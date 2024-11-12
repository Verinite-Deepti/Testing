package com.customerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.customerservice.model.Customer;
import com.customerservice.service.CustomerService;

@SpringJUnitConfig
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

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
        when(customerService.getCustomer(customerId)).thenReturn(Optional.of(customer));

        
        Optional<Customer> response = customerService.getCustomer(customerId);

       
        assertEquals(200, response.isPresent() ? 200 : 400);

    }
}


