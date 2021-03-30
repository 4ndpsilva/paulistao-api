package com.aps.paulistao.api.repository;

import com.aps.paulistao.api.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query(name = "findPartidasPorPeriodo",
            value = "SELECT * FROM TB_PARTIDA AS P " +
                    "WHERE P.DATA_HORA_PARTIDA BETWEEN DATEADD(HOUR, -3, CURRENT_TIMESTAMP) AND CURRENT_TIMESTAMP " +
                    "AND IFNULL(P.TEMPO_PARTIDA, 'Vazio') != 'Encerrado'",
            nativeQuery = true)
    List<Partida> findPartidasPorPeriodo();
}