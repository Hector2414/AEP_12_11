package com.cesumar.metria.service;


import com.cesumar.metria.exception.RecursoNaoEncontradoException;
import com.cesumar.metria.exception.RegraDeNegocioException;
import com.cesumar.metria.model.Equipamento;
import com.cesumar.metria.repository.EquipamentoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(
            EquipamentoRepository equipamentoRepository) {

        this.equipamentoRepository = equipamentoRepository;
    }

    @Transactional
    public Equipamento cadastrar(Equipamento equipamento) {

        equipamentoRepository.findByTag(equipamento.getTag())
                .ifPresent(e -> {
                    throw new RegraDeNegocioException(
                            "Já existe um equipamento com esta TAG."
                    );
                });

        return equipamentoRepository.save(equipamento);
    }

    @Transactional(readOnly = true)
    public Equipamento buscarPorId(Long id) {

        return equipamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Equipamento não encontrado."
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    @Transactional
    public void atualizarStatus(Long id, String novoStatus) {

        Equipamento equipamento = buscarPorId(id);

        equipamento.atualizarStatus(novoStatus);

        equipamentoRepository.save(equipamento);
    }

}
