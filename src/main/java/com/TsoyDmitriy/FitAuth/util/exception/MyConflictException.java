package com.TsoyDmitriy.FitAuth.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MyConflictException extends RuntimeException {

    public MyConflictException(String message) {
        super(message);
    }

    public static MyConflictException userNotFound() {
        return new MyConflictException("Пользователь не зарегистрирован");
    }

    public static MyConflictException roleNotFound() {
        return new MyConflictException("Ошибка загрузки данных аутентификации");
    }

    public static MyConflictException alreadyRegistered(String username) {
        return new MyConflictException("Пользователь " + username + " уже зарегистрирован");
    }
}
