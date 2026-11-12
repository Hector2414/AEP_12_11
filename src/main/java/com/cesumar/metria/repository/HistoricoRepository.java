package com.cesumar.metria.repository;

import com.cesumar.metria.model.Historico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long>{

    List<Historico> findByOrdemServicoId(Long ordemServicoId);

}
