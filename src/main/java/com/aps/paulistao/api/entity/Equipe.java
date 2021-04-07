package com.aps.paulistao.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_EQUIPE")
public class Equipe extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1l;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "URL_LOGO")
    private String urlLogo;
}