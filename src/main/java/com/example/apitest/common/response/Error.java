package com.example.apitest.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Error {

    private String field;
    private String message;
    private String invalidValue;
}
