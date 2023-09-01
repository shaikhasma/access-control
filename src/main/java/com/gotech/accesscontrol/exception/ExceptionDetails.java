package com.gotech.accesscontrol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String details;

    public ExceptionDetails() {
        timeStamp = LocalDateTime.now();
    }

}
