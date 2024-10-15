package com.playtomic.tests.wallet.service.stripe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class StripePayment {

    @NonNull
    private final String id;

    @JsonCreator
    public StripePayment(@JsonProperty(value = "id", required = true) String id) {
        this.id = id;
    }
}
