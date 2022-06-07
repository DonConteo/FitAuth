package com.TsoyDmitriy.FitAuth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyInternalServerErrorException extends RuntimeException {

    public MyInternalServerErrorException(String message) {
        super(message);
    }
}
