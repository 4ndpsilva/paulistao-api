package com.aps.paulistao.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ForbiddenException(){
        super();
    }

    public ForbiddenException(final String message){
        super(message);
    }

    public ForbiddenException(final Throwable cause){
        super(cause);
    }

    public ForbiddenException(final String message, final Throwable cause){
        super(message, cause);
    }
}