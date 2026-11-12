package com.cesumar.metria.repository;

import com.cesumar.metria.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    Optional<Equipamento> findByTag(String tag);
}
