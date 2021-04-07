package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.PartidaDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.mapper.GenericMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API REST de Partidas")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController extends BaseController<Partida, PartidaDTO> {
    public PartidaController(final GenericMapper<Partida, PartidaDTO> mapper){
        super(mapper);
    }
}