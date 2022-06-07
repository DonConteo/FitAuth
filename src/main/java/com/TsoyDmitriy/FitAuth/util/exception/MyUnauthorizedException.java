package com.TsoyDmitriy.FitAuth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyUnauthorizedException extends RuntimeException {

    public MyUnauthorizedException(String message) {
        super(message);
    }

    public static void expired() {
        throw new MyUnauthorizedException("Текущая сессия более не валидна");
    }
}