package com.aps.paulistao.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String nome;

    @NotBlank
    private String urlLogo;
}