package com.aps.paulistao.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(final HttpStatus httpStatus, final String message, final String path){
        super();
        this.status    = httpStatus.value();
        this.error     = httpStatus.name();
        this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
        this.message   = message;
        this.path      = path;
    }
}