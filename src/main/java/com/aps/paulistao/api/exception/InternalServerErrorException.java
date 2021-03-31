package com.aps.paulistao.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(){
        super();
    }

    public InternalServerErrorException(final String message){
        super(message);
    }

    public InternalServerErrorException(final Throwable cause){
        super(cause);
    }

    public InternalServerErrorException(final String message, final Throwable cause){
        super(message, cause);
    }
}