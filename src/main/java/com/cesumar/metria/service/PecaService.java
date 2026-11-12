package com.cesumar.metria.service;


import com.cesumar.metria.exception.RecursoNaoEncontradoException;
import com.cesumar.metria.exception.RegraDeNegocioException;

import com.cesumar.metria.model.Estoque;
import com.cesumar.metria.model.Peca;

import com.cesumar.metria.repository.EstoqueRepository;
import com.cesumar.metria.repository.PecaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;
    private final EstoqueRepository estoqueRepository;

    public PecaService(
            PecaRepository pecaRepository,
            EstoqueRepository estoqueRepository) {

        this.pecaRepository = pecaRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public Peca cadastrar(Peca peca) {

        pecaRepository.findByCodigo(peca.getCodigo())
                .ifPresent(p -> {
                    throw new RegraDeNegocioException(
                            "Já existe uma peça com este código."
                    );
                });

        Peca novaPeca = pecaRepository.save(peca);

        Estoque estoque = new Estoque();
        estoque.setPeca(novaPeca);
        estoque.setQuantidadeDisponivel(0);

        estoqueRepository.save(estoque);

        return novaPeca;
    }

    @Transactional(readOnly = true)
    public Peca buscarPorId(Long id) {

        return pecaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Peça não encontrada."
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Peca> listar() {
        return pecaRepository.findAll();
    }

}
