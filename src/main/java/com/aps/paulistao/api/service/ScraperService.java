package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.PartidaDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.util.ScraperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScraperService {
    private final ScraperUtil scraperUtil;
    private final PartidaService partidaService;

    public void verificarPartidasPorPeriodo(){
        final Integer quantidadePartida = partidaService.findQuantidadePartidasPorPeriodo();

        if(quantidadePartida > 0){
            final List<Partida> partidas = partidaService.findPartidasPorPeriodo();

            partidas.forEach(p -> {
                final String url = scraperUtil.montarUrlPesquisa(p.getMandante().getNome(), p.getVisitante().getNome(), p.getDataHoraPartida().toLocalDate().toString());
                final PartidaDTO dtoGoogle = scraperUtil.getInfoPartida(url);
                partidaService.update(p.getId(), p);
            });
        }
    }
}