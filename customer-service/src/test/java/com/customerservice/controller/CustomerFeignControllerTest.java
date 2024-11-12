package com.customerservice.controller;

import com.customerservice.model.Customer;
import com.customerservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class CustomerFeignControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerFeignController customerFeignController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerUsingFeignClient() {
      
        String customerId = "1";
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("Deepti Wani");
        customer.setEmail("dw@gmail.com");
        when(customerService.getCustomerUsingFeignClient(customerId)).thenReturn(Optional.of(customer));

      
        ResponseEntity<Customer> response = customerFeignController.getCustomer(customerId);

    
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(customer, response.getBody());
    }
}









