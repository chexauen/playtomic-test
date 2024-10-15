package com.playtomic.tests.wallet.persistence;

import com.playtomic.tests.wallet.model.MoneyMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyMovementRepository extends JpaRepository<MoneyMovement, Long> {

}
