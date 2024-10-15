package com.playtomic.tests.wallet.presentation.controller;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.PaymentsService;
import com.playtomic.tests.wallet.service.WalletsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    @Autowired
    private WalletsService walletsService;
    @Autowired
    private PaymentsService paymentsService;

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable Long walletId) {
        Wallet wallet = walletsService.getWallet(walletId);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/wallets/{walletId}/topup")
    public ResponseEntity<Wallet> topupWallet(@PathVariable Long walletId, @RequestBody TopupRequest topupRequest) {
        Wallet wallet = paymentsService.topupWallet(walletId, topupRequest);
        return ResponseEntity.ok(wallet);
    }
}
