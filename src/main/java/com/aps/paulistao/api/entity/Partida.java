package com.aps.paulistao.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_PARTIDA")
public class Partida implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String statusPartida;

    @ManyToOne
    @JoinColumn(name = "MANDANTE_ID")
    private Equipe mandante;

    @ManyToOne
    @JoinColumn(name = "VISITANTE_ID")
    private Equipe visitante;

    @Column(name = "PLACAR_EQUIPE_MANDANTE")
    private Integer placarEquipeMandante;

    @Column(name = "PLACAR_EQUIPE_VISITANTE")
    private Integer placarEquipeVisitante;

    @Column(name = "GOLS_EQUIPE_MANDANTE")
    private String golsEquipeMandante;

    @Column(name = "GOLS_EQUIPE_VISITANTE")
    private String golsEquipeVisitante;

    @Column(name = "PLACAR_PENALTIS_MANDANTE")
    private Integer placarPenaltisMandante;

    @Column(name = "PLACAR_PENALTIS_VISITANTE")
    private Integer placarPenaltisVisitante;

    @ApiModelProperty
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    @Column(name = "DATA_HORA_PARTIDA")
    private LocalDateTime dataHoraPartida;

    @Column(name = "LOCAL_PARTIDA")
    private String localPartida;

    @Column(name = "TEMPO_PARTIDA")
    private String tempoPartida;
}