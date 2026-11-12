package com.cesumar.metria.repository;

import com.cesumar.metria.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByEquipamentoId(Long equipamentoId);

    List<OrdemServico> findByResponsavelId(Long responsavelId);

    List<OrdemServico> findByStatus(String status);
}
