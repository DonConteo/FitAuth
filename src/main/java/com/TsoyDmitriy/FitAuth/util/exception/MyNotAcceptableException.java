package com.TsoyDmitriy.FitAuth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MyNotAcceptableException extends RuntimeException {

    public MyNotAcceptableException(String message) {
        super(message);
    }

    public static MyNotAcceptableException wrongVerificationCode() {
        return new MyNotAcceptableException("Неверный код подтверждения!");
    }
}
