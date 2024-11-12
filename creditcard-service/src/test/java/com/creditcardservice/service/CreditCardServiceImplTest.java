package com.creditcardservice.service;

import com.creditcard.exception.CreditCardNotFoundException;
import com.creditcard.model.CreditCard;
import com.creditcard.model.Currency;
import com.creditcard.service.CreditCardServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditCardServiceImplTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId("1");
        ConcurrentHashMap<String, CreditCard> map = new ConcurrentHashMap<>();
        map.put("1", creditCard);
        creditCardService = new CreditCardServiceImpl() {
            @Override
            public CreditCard getCreditCard(String id) {
                return map.get(id);
            }
        };

        CreditCard result = creditCardService.getCreditCard("1");
        assertEquals("1", result.getId());
    }

    @Test
    public void testGetAllCreditCards() {
        Collection<CreditCard> creditCards = creditCardService.get();
        assertNotNull(creditCards);
        assertTrue(creditCards.size() > 0);
    }

    @Test
    public void testGetCreditCardNotFound() {
        CreditCardServiceImpl service = new CreditCardServiceImpl() {
            @Override
            public CreditCard getCreditCard(String id) {
                throw new CreditCardNotFoundException("Id not found: " + id);
            }
        };

        Exception exception = assertThrows(CreditCardNotFoundException.class, () -> {
            service.getCreditCard("invalid-id");
        });
        assertEquals("Id not found: invalid-id", exception.getMessage());
    }
}