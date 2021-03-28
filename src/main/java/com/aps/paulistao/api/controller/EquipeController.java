package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.EquipeDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/equipes")
public class EquipeController {

    private final GenericMapper<Equipe, EquipeDTO> mapper;

    @PostMapping
    public ResponseEntity<EquipeDTO> save(@RequestBody EquipeDTO dto){
        final Equipe entity = mapper.toEntity(dto);
        entity.setNome(entity.getNome() + "----Mapper");
        return new ResponseEntity<>(mapper.toDTO(entity), HttpStatus.OK);
    }
}