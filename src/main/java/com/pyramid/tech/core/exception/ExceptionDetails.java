package com.pyramid.tech.core.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ExceptionDetails {
    private String message;
    //private String errorMessage;
    //private String fieldName;
    private LocalDateTime dateTime;
}
