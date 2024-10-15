package com.playtomic.tests.wallet.service.stripe.impl;


import com.playtomic.tests.wallet.service.stripe.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.service.stripe.StripeServiceException;
import com.playtomic.tests.wallet.service.stripe.StripeService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

public class StripeServiceTest {

    URI testUri = URI.create("http://localhost");
    @Mock
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    StripeService s = new StripeService(testUri, testUri, restTemplate);

    @BeforeEach
    public void setup(){
        Mockito.when(restTemplate.postForObject(ArgumentMatchers.any(),ArgumentMatchers.argThat(new ChargeMatcher(new BigDecimal(5))), ArgumentMatchers.any()))
                .thenThrow(new StripeAmountTooSmallException());
    }
    @Test
    public void test_exception() {
        Assertions.assertThrows(StripeAmountTooSmallException.class, () ->
                s.charge("4242 4242 4242 4242", new BigDecimal(5)));
    }

    @Test
    public void test_ok() throws StripeServiceException {
        s.charge("4242 4242 4242 4242", new BigDecimal(15));
    }

    private static class ChargeMatcher implements ArgumentMatcher<StripeService.ChargeRequest> {

        private final BigDecimal amount;

        public ChargeMatcher(BigDecimal amount){
            this.amount = amount;
        }
        @Override
        public boolean matches(StripeService.ChargeRequest chargeRequest) {
            return amount.equals(chargeRequest.getAmount());
        }
    }
}
