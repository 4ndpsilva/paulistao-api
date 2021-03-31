package com.aps.paulistao.api.service;

import com.aps.paulistao.api.dto.PartidaGoogleDTO;
import com.aps.paulistao.api.entity.Partida;
import com.aps.paulistao.api.util.ScraperUtil;
import com.aps.paulistao.api.util.StatusPartida;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScraperService {
    private final ScraperUtil scraperUtil;
    private final PartidaService partidaService;

    public void verificarPartidasPorPeriodo(){
        final List<Partida> partidas = partidaService.findPartidasPorPeriodo();

        if(partidas.size() > 0){
            partidas.forEach(p -> {
                final String url = scraperUtil.montarUrlPesquisa(p.getMandante().getNome(), p.getVisitante().getNome(), p.getDataHoraPartida().toLocalDate().toString());
                final PartidaGoogleDTO dtoGoogle = scraperUtil.getInfoPartida(url);

                if (dtoGoogle.getStatusPartida() != StatusPartida.PARTIDA_NAO_INICIADA) {
                    partidaService.update(p, dtoGoogle);
                }
            });
        }
    }
}