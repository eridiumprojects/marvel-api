package com.example.mymarvel.api.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class CustomErrorMessage {
    private final int statusCode;
    private final String message;
}
