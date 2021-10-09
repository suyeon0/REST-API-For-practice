package com.example.apitest.advice;

import com.example.apitest.common.exception.UserDupException;
import com.example.apitest.common.notification.ErrorManage;
import com.example.apitest.common.response.Error;
import com.example.apitest.common.response.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice(basePackages = "com.example.apitest")
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private Error error;
    private final ErrorManage errorManage;

    /**
     * res 내 error 필드에 담을 데이터 세팅
     *
     * @param field
     * @param message
     * @param value
     * @return Error
     */
    private Error makeErrorMessage(String field, String message, String value) {
        Error errorMessage = Error.builder()
            .field(field)
            .message(message)
            .invalidValue(value)
            .build();
        return errorMessage;
    }

    /**
     * error response 생성
     *
     * @param errorList
     * @param httpServletRequest
     * @param statusCode
     * @return ErrorResponse
     */
    private ErrorResponse makeErrorResponse(List<Error> errorList,
        HttpServletRequest httpServletRequest, String statusCode) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .errorList(errorList)
            .requestUrl(httpServletRequest.getRequestURI())
            .statusCode(statusCode)
            .build();
        return errorResponse;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity illegalArgumentException(IllegalArgumentException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        String field = null;
        String message = e.getMessage();
        String invalidValue = null;

        errorList.add(this.makeErrorMessage(field, message, invalidValue));

        log.error(e.toString());
        errorManage.manageError(e.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(makeErrorResponse(errorList, httpServletRequest, HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(value = UserDupException.class)
    public ResponseEntity userDupException(UserDupException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        String field = e.getField();
        String message = e.getMessage();
        String invalidValue = e.getValue();

        errorList.add(this.makeErrorMessage(field, message, invalidValue));

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(makeErrorResponse(errorList, httpServletRequest, HttpStatus.CONFLICT.toString()));
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e,
        HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {
            Stream<Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() - 1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            errorList.add(this.makeErrorMessage(field, message, invalidValue));

        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(makeErrorResponse(errorList, httpServletRequest, HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
        HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        String field = e.getName();
        String message = e.getMessage();
        String invalidValue = e.getValue().toString();

        errorList.add(this.makeErrorMessage(field, message, invalidValue));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(makeErrorResponse(errorList, httpServletRequest, HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity bindException(BindException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            String invalidValue = "";
            if (ObjectUtils.isNotEmpty(error.getRejectedValue().toString())) {
                invalidValue = error.getRejectedValue().toString();
            }
            errorList.add(this.makeErrorMessage(field, message, invalidValue));
        });

        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(makeErrorResponse(errorList, httpServletRequest, HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException e, HttpServletRequest httpServletRequest) {
        errorManage.manageError(e.toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
