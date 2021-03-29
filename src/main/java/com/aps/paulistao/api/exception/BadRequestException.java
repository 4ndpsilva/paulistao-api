package com.aps.paulistao.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BadRequestException(){
        super();
    }

    public BadRequestException(final String message){
        super(message);
    }

    public BadRequestException(final Throwable cause){
        super(cause);
    }

    public BadRequestException(final String message, final Throwable cause){
        super(message, cause);
    }
}