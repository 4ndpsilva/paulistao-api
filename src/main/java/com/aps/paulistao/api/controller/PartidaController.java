package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.PartidaDTO;
import com.aps.paulistao.api.dto.PartidaResponseDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.exception.StandardError;
import com.aps.paulistao.api.mapper.PartidaMapper;
import com.aps.paulistao.api.service.PartidaService;
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

@Api("API REST de partidas")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {
    private final PartidaService service;
    private final PartidaMapper mapper;
    private final ApplicationEventPublisher publisher;

    @ApiOperation("Busca de partida por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Partida.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Partida> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation("Listagem de Partidas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PartidaResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping
    public ResponseEntity<PartidaResponseDTO> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation("Inclusão de Partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Partida.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PostMapping
    public ResponseEntity<Partida> save(@RequestBody @Valid PartidaDTO dto, HttpServletResponse response){
        final Partida Partida = service.save(mapper.toEntity(dto));
        publisher.publishEvent(new CreateResourceEvent(this, Partida.getId(), response));
        final String uri = response.getHeader("Location");
        return ResponseEntity.created(URI.create(uri)).body(Partida);
    }

    @ApiOperation("Alteração de Partida")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PartidaDTO dto){
        service.update(id, mapper.toEntity(dto));
        return ResponseEntity.noContent().build();
    }
}