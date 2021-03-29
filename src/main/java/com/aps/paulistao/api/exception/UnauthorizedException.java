package com.aps.paulistao.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(){
        super();
    }

    public UnauthorizedException(final String message){
        super(message);
    }

    public UnauthorizedException(final Throwable cause){
        super(cause);
    }

    public UnauthorizedException(final String message, final Throwable cause){
        super(message, cause);
    }
}