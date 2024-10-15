package com.playtomic.tests.wallet.presentation.controller;

import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.PaymentsService;
import com.playtomic.tests.wallet.service.UsersService;
import com.playtomic.tests.wallet.service.WalletsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Profile("develop")
public class DevelopController {
    private final Logger log = LoggerFactory.getLogger(DevelopController.class);

    @Autowired
    private WalletsService walletsService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWalletForDeveloping() {
        User user = usersService.createUser(User.builder().email("a@a.com").password("pass").username("user").build());
        Wallet wallet = walletsService.saveWallet(Wallet.builder().owner(user).balance(BigDecimal.TEN).build());

        return ResponseEntity.ok(wallet);
    }
}
