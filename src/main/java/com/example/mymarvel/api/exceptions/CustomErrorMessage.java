package com.example.mymarvel.api.exceptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomErrorMessage {
    private final int statusCode;
    private final String message;
}
