package com.aps.paulistao.api.dto;

import com.aps.paulistao.api.util.StatusPartida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PartidaGoogleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dataHoraPartida;
    private String localPartida;

    private StatusPartida statusPartida;
    private String tempoPartida;

    private String nomeEquipeMandante;
    private String logoEquipeMandante;
    private Integer placarEquipeMandante;
    private String golsEquipeMandante;
    private Integer placarPenaltisMandante;

    private String nomeEquipeVisitante;
    private String logoEquipeVisitante;
    private Integer placarEquipeVisitante;
    private String golsEquipeVisitante;
    private Integer placarPenaltisVisitante;
}