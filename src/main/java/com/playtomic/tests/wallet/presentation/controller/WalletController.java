package com.playtomic.tests.wallet.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    private final Logger log = LoggerFactory.getLogger(WalletController.class);

    @RequestMapping("/wallets/{walletId}")
    void getWallet(@PathVariable Long walletId) {
        log.info("Logging from /");
    }
}
