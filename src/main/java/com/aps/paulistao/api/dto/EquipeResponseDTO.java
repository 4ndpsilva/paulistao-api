package com.aps.paulistao.api.dto;

import com.aps.paulistao.api.entity.Equipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Equipe> equipes;
}