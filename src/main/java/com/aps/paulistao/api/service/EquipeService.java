package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.Equipe;
import com.aps.paulistao.api.exception.BadRequestException;
import com.aps.paulistao.api.exception.NotFoundException;
import com.aps.paulistao.api.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipeService extends BaseService<Equipe>{
    @Autowired
    private EquipeRepository repository;

    public EquipeService(){
        super(Equipe.class);
    }

    @Override
    public Equipe save(final Equipe equipe) {
        validate(equipe);
        equipe.setNome(equipe.getNome().toUpperCase());
        return super.save(equipe);
    }

    public Equipe findByNome(final String nome){
        return repository.findByNome(nome.toUpperCase())
                .orElseThrow(() -> new NotFoundException("Equipe não encontrada com o nome informado: "+nome));
    }

    public EquipeResponseDTO findAll() {
        return new EquipeResponseDTO(super.find());
    }

    private void validate(final Equipe equipeValidate){
        final Long id = equipeValidate.getId();
        final String nome = equipeValidate.getNome();

        final Optional<Equipe> opEquipe = repository.findByNome(nome.toUpperCase());

        if(opEquipe.isPresent()){
            final Equipe equipe = opEquipe.get();

            if(id == null && nome.equalsIgnoreCase(equipe.getNome())){
                throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado: " +nome);
            }

            if(id != equipe.getId() && nome.equalsIgnoreCase(equipe.getNome())){
                throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado: " +nome);
            }
        }
    }
}