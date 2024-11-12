package com.creditcardservice.service;

import com.creditcard.model.CreditCard;
import com.creditcard.model.Currency;
import com.creditcard.service.CreditCardServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardServiceImplRepositoryTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitializeCreditCard() {
        Collection<CreditCard> creditCards = creditCardService.get();
        assertNotNull(creditCards);
        assertTrue(creditCards.size() > 0);
    }
}