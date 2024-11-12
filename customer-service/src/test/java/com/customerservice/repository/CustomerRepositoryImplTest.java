package com.customerservice.repository;

import com.customerservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerRepositoryImplTest {

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

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

     
        Customer result = customerRepository.getCustomer(customerId);

        
        assertEquals(customerId, result.getCustomerId());
        assertEquals("Deepti Wani", result.getName());
    }
}





