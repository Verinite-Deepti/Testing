package com.customerservice.repository;

import com.customerservice.model.CreditCard;
import com.customerservice.model.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardCacheRepositoryImplTest {

    @InjectMocks
    private CreditCardCacheRepositoryImpl creditCardCacheRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCreditCard() {
       
        String creditCardId = "1";
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardId);
        creditCard.setBalance(170000.00);
        creditCard.setCurrency(Currency.INR);
        
        CreditCard result = creditCardCacheRepository.getCreditCard(creditCardId);
        
        assertEquals(creditCardId, result.getId());
        assertEquals(170000.00, result.getBalance());
    }
}