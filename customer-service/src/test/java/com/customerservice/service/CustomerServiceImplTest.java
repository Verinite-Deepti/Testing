package com.customerservice.service;

import com.customerservice.model.Customer;
import com.customerservice.config.CustomerFeignClient;
import com.customerservice.model.CreditCard;
import com.customerservice.model.Currency;
import com.customerservice.repository.CustomerRepository;
import com.customerservice.repository.CreditCardCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CreditCardCacheRepository creditCardCacheRepository;

    @Mock
    private CustomerFeignClient customerFeignClient;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomer() {
     
        String customerId = "1";
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("Deepti Wani");
        customer.setEmail("dw@gmail.com");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(customerId);
        creditCard.setBalance(170000.00);
        creditCard.setCurrency(Currency.INR);

        when(customerRepository.getCustomer(customerId)).thenReturn(customer);
        when(restTemplate.getForEntity("http://creditcard-service/creditcard/" + customerId, CreditCard.class))
                .thenReturn(new ResponseEntity<>(creditCard, HttpStatus.OK));


        Optional<Customer> result = customerService.getCustomer(customerId);

   
        assertEquals(customerId, result.get().getCustomerId());
        assertEquals(creditCard.getBalance(), result.get().getCreditCard());
        assertEquals(creditCard.getCurrency(), result.get().getCurrency());
    }

    @Test
    void testGetCustomerUsingFeignClient() {
      
        String customerId = "1";
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("Deepti Wani");
        customer.setEmail("dw@gmail.com");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(customerId);
        creditCard.setBalance(170000.00);
        creditCard.setCurrency(Currency.INR);

        when(customerRepository.getCustomer(customerId)).thenReturn(customer);
        when(customerFeignClient.getCreditCard(customerId)).thenReturn(creditCard);

       
        Optional<Customer> result = customerService.getCustomerUsingFeignClient(customerId);

     
        assertEquals(customerId, result.get().getCustomerId());
        assertEquals(creditCard.getBalance(), result.get().getCreditCard());
        assertEquals(creditCard.getCurrency(), result.get().getCurrency());
    }
}