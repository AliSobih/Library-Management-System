package com.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomValidationExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    Map<String, String> errors;
}
