package com.playtomic.tests.wallet.service;


import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.stripe.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.service.stripe.StripeService;
import com.playtomic.tests.wallet.service.stripe.StripeServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

public class PaymentsServiceTest {

    PaymentsService service;

    WalletsService walletsService;

    StripeService stripeService;

    MoneyMovementService moneyMovementService;

    @BeforeEach
    public void setup(){
        walletsService = Mockito.mock(WalletsService.class);
        stripeService = Mockito.mock(StripeService.class);
        moneyMovementService = Mockito.mock(MoneyMovementService.class);
        Mockito.when(walletsService.getWalletForUpdate(1L)).thenReturn(Wallet.builder().balance(BigDecimal.ONE).build());
        service = new PaymentsService(walletsService,stripeService,moneyMovementService);
    }
    @Test
    public void test_TopUpWorks() throws StripeServiceException {
        Wallet wallet = service.topupWallet(1L, new TopupRequest("123", BigDecimal.TEN));
        Assertions.assertEquals(wallet.getBalance(),new BigDecimal(11L));
    }

    @Test
    public void test_StripeFails() throws StripeServiceException {
        Mockito.when(stripeService.charge(ArgumentMatchers.anyString(),ArgumentMatchers.any())).thenThrow(new StripeAmountTooSmallException());
        try{
            service.topupWallet(1L, new TopupRequest("123", BigDecimal.TEN));
            AssertionErrors.fail("It should throw StripeAmountTooSmallException");
        } catch (StripeAmountTooSmallException e){
            //pass
        } catch (Exception e){
            AssertionErrors.fail("It should throw StripeAmountTooSmallException");
        }
    }
}
