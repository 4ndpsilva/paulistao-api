package com.aps.paulistao.api.repository;

import com.aps.paulistao.api.entity.Equipe;

import java.util.Optional;

public interface EquipeRepository extends BaseRepository<Equipe> {
    Optional<Equipe> findByNome(final String nome);

    boolean existsByNome(final String nome);
}