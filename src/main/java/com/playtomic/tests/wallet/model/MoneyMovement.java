package com.playtomic.tests.wallet.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "MONEY_MOVEMENT")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyMovement extends IdentifiableModel{

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
}
