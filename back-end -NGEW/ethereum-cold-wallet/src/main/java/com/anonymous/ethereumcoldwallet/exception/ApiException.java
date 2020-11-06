package com.anonymous.ethereumcoldwallet.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {

    private final String message;
    private final int status;
    private final ZonedDateTime timestamp;
    private final String path;
    private final boolean success;

}
