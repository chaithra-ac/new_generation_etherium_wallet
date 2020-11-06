package com.anonymous.ethereumcoldwallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleApiRequestException(Exception e,
                                                               HttpServletRequest httpServletRequest) {
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .path(httpServletRequest.getServletPath())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
