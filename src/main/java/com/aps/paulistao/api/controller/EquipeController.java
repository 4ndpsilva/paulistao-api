package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.EquipeDTO;
import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.exception.StandardError;
import com.aps.paulistao.api.mapper.GenericMapper;
import com.aps.paulistao.api.service.EquipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@Api("API REST de equipes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController {
    private final EquipeService service;
    private final GenericMapper<Equipe, EquipeDTO> mapper;
    private final ApplicationEventPublisher publisher;

    @ApiOperation("Busca de equipe por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Equipe.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipe> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation("Listagem de equipes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EquipeResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping
    public ResponseEntity<EquipeResponseDTO> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation("Inclusão de equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Equipe.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PostMapping
    public ResponseEntity<Equipe> save(@RequestBody @Valid EquipeDTO dto, HttpServletResponse response){
        final Equipe equipe = service.save(mapper.toEntity(dto));
        publisher.publishEvent(new CreateResourceEvent(this, equipe.getId(), response));
        final String uri = response.getHeader("Location");
        return ResponseEntity.created(URI.create(uri)).body(equipe);
    }

    @ApiOperation("Alteração de equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid EquipeDTO dto){
        final Equipe equipe = service.update(id, mapper.toEntity(dto));

        return ResponseEntity.noContent().build();
    }
}