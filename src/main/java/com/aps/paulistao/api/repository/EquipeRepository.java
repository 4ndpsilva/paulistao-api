package com.aps.paulistao.api.repository;

import com.aps.paulistao.api.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    Optional<Equipe> findByNome(final String nome);

    boolean existsByNome(final String nome);
}