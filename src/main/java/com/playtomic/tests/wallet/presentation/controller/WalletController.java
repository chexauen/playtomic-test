package com.playtomic.tests.wallet.presentation.controller;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletsService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class WalletController {
    private final Logger log = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletsService walletsService;

    @RequestMapping("/wallets/{walletId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable Long walletId) {
        Wallet wallet = walletsService.getWallet(walletId);
        return ResponseEntity.ok(wallet);
    }
}
