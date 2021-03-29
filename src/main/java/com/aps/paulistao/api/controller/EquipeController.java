package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.exception.StandardError;
import com.aps.paulistao.api.service.EquipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API REST de equipes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController {
    private final EquipeService service;

    @ApiOperation("Busca equipe por id")
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

    @ApiOperation("Listar equipes")
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
}