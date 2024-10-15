package com.playtomic.tests.wallet.presentation.controller;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.PaymentsService;
import com.playtomic.tests.wallet.service.WalletsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Retrieves a wallet by its id",
            tags = {"Wallet"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Wallet.class), mediaType = "application/json")
            })
    })
    public ResponseEntity<Wallet> getWallet(@PathVariable Long walletId) {
        Wallet wallet = walletsService.getWallet(walletId);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/wallets/{walletId}/topup")
    @Operation(
            summary = "Tops up a wallet",
            tags = {"Wallet"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Wallet.class), mediaType = "application/json")
            })
    })
    public ResponseEntity<Wallet> topupWallet(@PathVariable Long walletId, @RequestBody TopupRequest topupRequest) {
        Wallet wallet = paymentsService.topupWallet(walletId, topupRequest);
        return ResponseEntity.ok(wallet);
    }
}
