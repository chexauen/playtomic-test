package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.MoneyMovement;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.stripe.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentsService {

    @Autowired
    WalletsService walletsService;

    @Autowired
    StripeService stripeService;

    @Autowired
    MoneyMovementService moneyMovementService;

    @Transactional
    public Wallet topupWallet(Long walletId, TopupRequest topupRequest) {
        Wallet wallet = walletsService.getWallet(walletId);
        wallet.setBalance(wallet.getBalance().add(topupRequest.amount()));
        walletsService.saveWallet(wallet);
        MoneyMovement moneyMovement = MoneyMovement.builder().wallet(wallet).amount(topupRequest.amount()).build();
        moneyMovementService.saveMoneyMovement(moneyMovement);
        //most expensive action at the end, if it fails all previous changes would be rolled back
        stripeService.charge(topupRequest.cardNumber(), topupRequest.amount());
        return wallet;
    }
}
