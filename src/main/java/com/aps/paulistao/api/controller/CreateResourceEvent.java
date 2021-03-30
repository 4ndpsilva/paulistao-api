package com.aps.paulistao.api.controller;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class CreateResourceEvent extends ApplicationEvent {
    private Long id;
    private HttpServletResponse response;

    public CreateResourceEvent(final Object obj, final Long id, final HttpServletResponse response){
        super(obj);

        this.id = id;
        this.response = response;
    }
}