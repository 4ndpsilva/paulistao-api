package com.aps.paulistao.api.dto;

import com.aps.paulistao.api.util.StatusPartida;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "dd/mm/yyyy hh:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
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