package com.anonymous.ethereumcoldwallet.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

public class ResponseHandler {

    public static ResponseEntity generateResponse(HttpStatus status, boolean success,
                                                  String message, Object responseData, Object error) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .message(message)
                        .success(success)
                        .data(responseData)
                        .error(error)
                        .build(), status);
    }
}