package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.PartidaResponseDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.exception.NotFoundException;
import com.aps.paulistao.api.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartidaService {
    private final PartidaRepository repository;

    private final EquipeService equipeService;


    public Partida findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partida não encontrada com o id informado: "+id));
    }

    public PartidaResponseDTO findAll() {
        return new PartidaResponseDTO(repository.findAll());
    }

    public Partida save(final Partida partida) {
        partida.setMandante(equipeService.findByNome(partida.getMandante().getNome()));
        partida.setVisitante(equipeService.findByNome(partida.getVisitante().getNome()));
        return repository.save(partida);
    }

    public void update(final Long id, final Partida partidaUpdate) {
        final Partida partida = findById(id);
        BeanUtils.copyProperties(partidaUpdate, partida, "id");
        save(partida);
    }
}