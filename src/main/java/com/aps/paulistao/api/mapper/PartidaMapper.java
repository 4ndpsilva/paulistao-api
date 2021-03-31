package com.aps.paulistao.api.mapper;

import com.aps.paulistao.api.dto.PartidaDTO;
import com.aps.paulistao.api.entity.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartidaMapper extends GenericMapper<Partida, PartidaDTO> {
    @Mapping(source = "dto.nomeEquipeMandante", target = "mandante.nome")
    @Mapping(source = "dto.nomeEquipeVisitante", target = "visitante.nome")
    @Override
    Partida toEntity(final PartidaDTO dto);

    @Mapping(source = "entity.mandante.nome", target = "nomeEquipeMandante")
    @Mapping(source = "entity.visitante.nome", target = "nomeEquipeVisitante")
    @Override
    PartidaDTO toDTO(final Partida entity);
}