package com.example.apitest.common.exception;

/**
 * user 중복 exception
 */
public class UserDupException extends RuntimeException {

    private final String value;
    private final String message;
    private final String field;

    public UserDupException(String email) {
        this.message = "해당 유저가 이미 존재합니다";
        this.value = email;
        this.field = "email";
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
