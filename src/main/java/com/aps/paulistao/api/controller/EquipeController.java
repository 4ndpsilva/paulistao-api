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

}