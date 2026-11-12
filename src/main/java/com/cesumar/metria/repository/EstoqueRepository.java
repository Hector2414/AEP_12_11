package com.cesumar.metria.repository;

import com.cesumar.metria.model.Estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByPecaId(Long pecaId);

}
