package com.playtomic.tests.wallet.presentation.request;

import java.math.BigDecimal;

public record TopupRequest(String cardNumber, BigDecimal amount) {

}
