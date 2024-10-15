package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.persistence.UserRepository;
import com.playtomic.tests.wallet.persistence.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletsService {
    @Autowired
    private WalletRepository walletRepository;

    public Wallet getWalletForUpdate(Long id){
        return walletRepository.findById_(id).orElseThrow(() -> new EntityNotFoundException("Resource not found: " + id));
    }
    public Wallet getWallet(Long id){
        return walletRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found: " + id));
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);

    }
}
