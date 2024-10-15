package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.MoneyMovement;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentsService {

    @Autowired
    private final WalletsService walletsService;

    @Autowired
    private final StripeService stripeService;

    @Autowired
    private final MoneyMovementService moneyMovementService;

    @Transactional
    public Wallet topupWallet(Long walletId, TopupRequest topupRequest) {
        Wallet wallet = walletsService.getWalletForUpdate(walletId);
        wallet.setBalance(wallet.getBalance().add(topupRequest.amount()));
        walletsService.saveWallet(wallet);
        MoneyMovement moneyMovement = MoneyMovement.builder().wallet(wallet).amount(topupRequest.amount()).build();
        moneyMovementService.saveMoneyMovement(moneyMovement);
        //most expensive action at the end, if it fails all previous changes would be rolled back
        stripeService.charge(topupRequest.cardNumber(), topupRequest.amount());
        log.info("wallet with id: {} topped up by {}", walletId, topupRequest.amount());
        return wallet;
    }
}
