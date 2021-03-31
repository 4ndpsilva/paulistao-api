package com.aps.paulistao.api.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreateResourceListener implements ApplicationListener<CreateResourceEvent> {
    @Override
    public void onApplicationEvent(CreateResourceEvent event) {
        final Long id = event.getId();
        final HttpServletResponse response = event.getResponse();

        addHeaderLocation(id, response);
    }

    private void addHeaderLocation(final Long id, final HttpServletResponse response){
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        response.setHeader("Location", location.toASCIIString());
    }
}