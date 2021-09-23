package com.example.apitest.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T> {

    private String statusCode;
    private String responseMessage;
    private T data;

    public DefaultRes(final String statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static <T> DefaultRes<T> res(final String statusCode, final String responseMessage) {
        return res(statusCode, responseMessage, null);
    }

    public static <T> DefaultRes<T> res(final String statusCode, final String responseMessage,
        final T t) {
        return DefaultRes.<T>builder()
            .data(t)
            .statusCode(statusCode)
            .responseMessage(responseMessage)
            .build();
    }
}
