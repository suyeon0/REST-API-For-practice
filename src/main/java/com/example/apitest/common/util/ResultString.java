package com.example.apitest.common.util;

public enum ResultString {
    USER_LOGIN_SUCCESS(0),
    USER_INCORRECT_PWD(1),
    USER_INCORRECT_EMAIL(2);

    private final int value;

    ResultString(int value) {
        this.value = value;
    }
}
