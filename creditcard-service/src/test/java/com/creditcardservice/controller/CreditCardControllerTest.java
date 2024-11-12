package com.creditcardservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creditcard.controller.CreditCardController;
import com.creditcard.model.CreditCard;
import com.creditcard.model.Currency;
import com.creditcard.service.CreditCardService;

public class CreditCardControllerTest {

    @Mock
    private CreditCardService creditcardService;

    @InjectMocks
    private CreditCardController creditcardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive test scenario: credit card exists
    @Test
    void testGetCreditCard_Success() {
        String id = "1";
        CreditCard creditcard = new CreditCard();
        creditcard.setId(id);
        creditcard.setBalance(170000.00);
        creditcard.setCurrency(Currency.INR);
        
        when(creditcardService.getCreditCard(id)).thenReturn(creditcard);

        ResponseEntity<CreditCard> response = creditcardController.getCreditCard(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditcard, response.getBody());
    }

    @Test
    void testGetCreditCard_NotFound() {
        String id = "2";

        when(creditcardService.getCreditCard(id)).thenReturn(null);

        ResponseEntity<CreditCard> response = creditcardController.getCreditCard(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}