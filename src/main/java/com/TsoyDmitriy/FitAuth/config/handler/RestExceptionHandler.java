package com.TsoyDmitriy.FitAuth.config.handler;

import com.TsoyDmitriy.FitAuth.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

import static org.hibernate.validator.internal.metadata.core.ConstraintHelper.MESSAGE;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MyNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> handleCustomException(MyNotAcceptableException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCustomException(MyNotFoundException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleCustomException(MyConflictException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleCustomException(MyForbiddenException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleCustomException(MyBadRequestException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleCustomException(MyInternalServerErrorException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyLockedException.class)
    @ResponseStatus(HttpStatus.LOCKED)
    public Map<String, String> handleCustomException(MyLockedException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MyUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleCustomException(MyUnauthorizedException ex) {
        return Collections.singletonMap(MESSAGE, ex.getMessage());
    }
}
