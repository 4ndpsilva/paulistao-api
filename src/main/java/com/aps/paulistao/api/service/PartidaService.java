package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.PartidaGoogleDTO;
import com.aps.paulistao.api.dto.PartidaResponseDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService extends BaseService<Partida>{
    @Autowired
    private PartidaRepository repository;

    @Autowired
    private EquipeService equipeService;

    public PartidaService(){
        super(Partida.class);
    }

    public Partida save(final Partida partida) {
        partida.setMandante(equipeService.findByNome(partida.getMandante().getNome()));
        partida.setVisitante(equipeService.findByNome(partida.getVisitante().getNome()));
        return super.save(partida);
    }

    public void update(final Partida partida, final PartidaGoogleDTO partidaGoogle) {
        partida.setPlacarEquipeMandante(partidaGoogle.getPlacarEquipeMandante());
        partida.setPlacarEquipeMandante(partidaGoogle.getPlacarEquipeMandante());
        partida.setGolsEquipeMandante(partidaGoogle.getGolsEquipeMandante());
        partida.setGolsEquipeVisitante(partidaGoogle.getGolsEquipeVisitante());
        partida.setPlacarPenaltisMandante(partidaGoogle.getPlacarPenaltisMandante());
        partida.setPlacarPenaltisVisitante(partidaGoogle.getPlacarPenaltisVisitante());
        partida.setTempoPartida(partidaGoogle.getTempoPartida());

        save(partida);
    }

    public List<Partida> findPartidasPorPeriodo() {
        return repository.findPartidasPorPeriodo();
    }

    public PartidaResponseDTO findAll() {
        return new PartidaResponseDTO(super.find());
    }
}