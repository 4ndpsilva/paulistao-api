package com.aps.paulistao.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(final BadRequestException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.BAD_REQUEST, e, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> handleUnauthorizedException(final UnauthorizedException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.UNAUTHORIZED, e, request);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardError> handleForbiddenException(final ForbiddenException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.FORBIDDEN, e, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(final NotFoundException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StandardError> handleInternalServerErrorException(final InternalServerErrorException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> handleRuntimeException(final RuntimeException e, HttpServletRequest request){
        return getResponseEntityError(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).body(new StandardError(status, ex.getMessage(), request.getContextPath()));
    }

    private ResponseEntity<StandardError> getResponseEntityError(final HttpStatus httpStatus, final RuntimeException e, final HttpServletRequest request){
        return ResponseEntity.status(httpStatus).body(new StandardError(httpStatus, e.getMessage(), request.getRequestURI()));
    }
}