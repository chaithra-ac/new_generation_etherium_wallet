package com.anonymous.ethereumcoldwallet.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;


/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    private LocalDateTime timestamp;

    private int status;

    private String message;

    private boolean success;

    private T data;

    private Object error;

}