package com.aps.paulistao.api.mapper;

import com.aps.paulistao.api.dto.EquipeDTO;
import com.aps.paulistao.api.entity.Equipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipeMapper extends GenericMapper<Equipe, EquipeDTO> { }