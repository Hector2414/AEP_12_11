package com.cesumar.metria.service;



import com.cesumar.metria.exception.RecursoNaoEncontradoException;
import com.cesumar.metria.exception.RegraDeNegocioException;

import com.cesumar.metria.model.Estoque;
import com.cesumar.metria.model.Peca;

import com.cesumar.metria.repository.EstoqueRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final PecaService pecaService;

    public EstoqueService(
            EstoqueRepository estoqueRepository,
            PecaService pecaService) {

        this.estoqueRepository = estoqueRepository;
        this.pecaService = pecaService;
    }

    @Transactional(readOnly = true)
    public Estoque buscarPorPeca(Long pecaId) {

        return estoqueRepository.findByPecaId(pecaId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Estoque não encontrado para esta peça."
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    @Transactional
    public Estoque adicionarEstoque(
            Long pecaId,
            Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new RegraDeNegocioException(
                    "A quantidade deve ser maior que zero."
            );
        }

        Estoque estoque = buscarPorPeca(pecaId);

        estoque.adicionar(quantidade);

        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque baixarEstoque(
            Long pecaId,
            Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new RegraDeNegocioException(
                    "A quantidade deve ser maior que zero."
            );
        }

        Estoque estoque = buscarPorPeca(pecaId);

        if (quantidade > estoque.getQuantidadeDisponivel()) {
            throw new RegraDeNegocioException(
                    "Estoque insuficiente para a peça: "
                            + estoque.getPeca().getNome()
            );
        }

        estoque.baixar(quantidade);

        return estoqueRepository.save(estoque);
    }

    @Transactional(readOnly = true)
    public boolean verificarAlertaEstoque(Long pecaId) {

        Estoque estoque = buscarPorPeca(pecaId);

        return estoque.estaAbaixoDoMinimo();
    }

    @Transactional(readOnly = true)
    public String obterSituacaoEstoque(Long pecaId) {

        Estoque estoque = buscarPorPeca(pecaId);

        if (estoque.estaAbaixoDoMinimo()) {
            return "ALERTA: Estoque abaixo do mínimo.";
        }

        return "ESTOQUE NORMAL";
    }

}
