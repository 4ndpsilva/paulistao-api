package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.EquipeDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.mapper.GenericMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API REST de Equipes")
@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController extends BaseController<Equipe, EquipeDTO>{
    @Autowired
    public EquipeController(final GenericMapper<Equipe, EquipeDTO> mapper){
        super(mapper);
    }
}