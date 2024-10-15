package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.MoneyMovement;
import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.persistence.MoneyMovementRepository;
import com.playtomic.tests.wallet.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoneyMovementService {
    @Autowired
    private MoneyMovementRepository moneyMovementRepository;

    public MoneyMovement saveMoneyMovement(MoneyMovement moneyMovement) {
        return moneyMovementRepository.save(moneyMovement);
    }
}
