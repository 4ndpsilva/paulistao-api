package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.exception.NotFoundException;
import com.aps.paulistao.api.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EquipeService {
    private EquipeRepository repository;

    public Equipe findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Equipe não encontrada com o id "+id));
    }

    public EquipeResponseDTO findAll() {
        return new EquipeResponseDTO(repository.findAll());
    }
}