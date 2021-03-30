package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.exception.BadRequestException;
import com.aps.paulistao.api.exception.NotFoundException;
import com.aps.paulistao.api.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EquipeService {
    private final EquipeRepository repository;

    public Equipe findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipe não encontrada com o id informado: "+id));
    }

    public Equipe findByNome(final String nome){
        return repository.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Equipe não encontrada com o nome informado: "+nome));
    }

    public EquipeResponseDTO findAll() {
        return new EquipeResponseDTO(repository.findAll());
    }

    public Equipe save(final Equipe equipe) {
        validate(equipe);
        equipe.setNome(equipe.getNome().toUpperCase());
        return repository.save(equipe);
    }

    public Equipe update(final Long id, final Equipe equipeUpdate) {
        final Equipe equipe = findById(id);
        BeanUtils.copyProperties(equipeUpdate, equipe, "id");
        return save(equipe);
    }

    private void validate(final Equipe equipeValidate){
        final Long id = equipeValidate.getId();
        final String nome = equipeValidate.getNome();

        final Optional<Equipe> opEquipe = repository.findByNome(nome.toUpperCase());

        if(opEquipe.isPresent()){
            final Equipe equipe = opEquipe.get();

            if(id == null && nome.equalsIgnoreCase(equipe.getNome())){
                throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado");
            }

            if(id != equipe.getId() && nome.equalsIgnoreCase(equipe.getNome())){
                throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado");
            }
        }
    }
}