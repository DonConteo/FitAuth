package com.TsoyDmitriy.FitAuth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.LOCKED)
public class MyLockedException extends RuntimeException {

    public MyLockedException(String message) {
        super(message);
    }

    public static MyLockedException frequentVerificationCall(Integer interval) {
        return new MyLockedException(
                format("С момента последнего запроса прошло менее %s секунд", interval));
    }
}
