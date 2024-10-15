package com.playtomic.tests.wallet.presentation.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

    private final int statusCode;
    private final LocalDate timestamp;
    private final String message;
    private final String description;

}
