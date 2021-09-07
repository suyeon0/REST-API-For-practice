package com.example.apitest.common.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorResponse {

    String statusCode;
    String requestUrl;
    List<Error> errorList;
}
