package com.aps.paulistao.api.dto;

import com.aps.paulistao.api.util.StatusPartida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PartidaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private StatusPartida statusPartida;
    private String tempoPartida;
    
    private String nomeEquipeMandante;
    private String urlLogoEquipeMandante;
    private Integer placarEquipeMandante;
    private String golsEquipeMandante;
    private Integer placarPenaltisEquipeMandante;

    private String nomeEquipeVisitante;
    private String urlLogoEquipeVisitante;
    private Integer placarEquipeVisitante;
    private String golsEquipeVisitante;
    private Integer placarPenaltisEquipeVisitante;
}