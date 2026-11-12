package com.cesumar.metria.service;


import com.cesumar.metria.exception.RecursoNaoEncontradoException;
import com.cesumar.metria.exception.RegraDeNegocioException;

import com.cesumar.metria.model.*;

import com.cesumar.metria.repository.ItemOrdemServicoRepository;
import com.cesumar.metria.repository.OrdemServicoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final ItemOrdemServicoRepository itemRepository;

    private final EquipamentoService equipamentoService;
    private final FuncionarioService funcionarioService;
    private final PecaService pecaService;
    private final EstoqueService estoqueService;

    public OrdemServicoService(
            OrdemServicoRepository ordemServicoRepository,
            ItemOrdemServicoRepository itemRepository,
            EquipamentoService equipamentoService,
            FuncionarioService funcionarioService,
            PecaService pecaService,
            EstoqueService estoqueService) {

        this.ordemServicoRepository = ordemServicoRepository;
        this.itemRepository = itemRepository;
        this.equipamentoService = equipamentoService;
        this.funcionarioService = funcionarioService;
        this.pecaService = pecaService;
        this.estoqueService = estoqueService;
    }

    /*
     * ABRIR ORDEM DE SERVIÇO
     */
    @Transactional
    public OrdemServico abrir(
            Long equipamentoId,
            Long responsavelId,
            String tipo) {

        Equipamento equipamento =
                equipamentoService.buscarPorId(equipamentoId);

        Funcionario responsavel =
                funcionarioService.buscarPorId(responsavelId);

        // Regra: funcionário deve estar ativo
        funcionarioService.validarResponsavelAtivo(responsavel);

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setEquipamento(equipamento);
        ordemServico.setResponsavel(responsavel);
        ordemServico.setTipo(tipo);

        // Método de negócio da própria entidade
        ordemServico.abrir();

        return ordemServicoRepository.save(ordemServico);
    }

    /*
     * BUSCAR POR ID
     */
    @Transactional(readOnly = true)
    public OrdemServico buscarPorId(Long id) {

        return ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Ordem de Serviço não encontrada."
                        )
                );
    }

    /*
     * LISTAR TODAS
     */
    @Transactional(readOnly = true)
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    /*
     * ADICIONAR PEÇA À OS
     *
     * Também realiza a baixa automática do estoque.
     */
    @Transactional
    public OrdemServico adicionarPeca(
            Long ordemServicoId,
            Long pecaId,
            Integer quantidade) {

        OrdemServico ordemServico =
                buscarPorId(ordemServicoId);

        if ("ENCERRADA".equals(ordemServico.getStatus())) {
            throw new RegraDeNegocioException(
                    "Não é possível adicionar peças a uma OS encerrada."
            );
        }

        Peca peca =
                pecaService.buscarPorId(pecaId);

        /*
         * Baixa o estoque.
         *
         * Se não houver quantidade suficiente,
         * será lançada uma exceção e toda a
         * transação será cancelada.
         */
        estoqueService.baixarEstoque(
                pecaId,
                quantidade
        );

        ItemOrdemServico item =
                new ItemOrdemServico();

        item.setPeca(peca);
        item.setQuantidade(quantidade);

        ordemServico.adicionarItem(item);

        /*
         * Como existe CascadeType.ALL,
         * salvar a OS também salva o item.
         */
        return ordemServicoRepository.save(ordemServico);
    }

    /*
     * REMOVER PEÇA DA OS
     *
     * Regra adicional importante:
     * devolve a quantidade ao estoque.
     */
    @Transactional
    public OrdemServico removerPeca(
            Long ordemServicoId,
            Long itemId) {

        OrdemServico ordemServico =
                buscarPorId(ordemServicoId);

        if ("ENCERRADA".equals(ordemServico.getStatus())) {
            throw new RegraDeNegocioException(
                    "Não é possível alterar uma OS encerrada."
            );
        }

        ItemOrdemServico item =
                itemRepository.findById(itemId)
                        .orElseThrow(() ->
                                new RecursoNaoEncontradoException(
                                        "Item da OS não encontrado."
                                )
                        );

        // Segurança: confirma se o item pertence à OS
        if (!item.getOrdemServico()
                .getId()
                .equals(ordemServico.getId())) {

            throw new RegraDeNegocioException(
                    "Este item não pertence à Ordem de Serviço informada."
            );
        }

        /*
         * Devolve a peça ao estoque
         */
        estoqueService.adicionarEstoque(
                item.getPeca().getId(),
                item.getQuantidade()
        );

        ordemServico.removerItem(item);

        return ordemServicoRepository.save(ordemServico);
    }

    /*
     * ENCERRAR OS
     */
    @Transactional
    public OrdemServico encerrar(Long ordemServicoId) {

        OrdemServico ordemServico =
                buscarPorId(ordemServicoId);

        if ("ENCERRADA".equals(ordemServico.getStatus())) {
            throw new RegraDeNegocioException(
                    "Esta Ordem de Serviço já está encerrada."
            );
        }

        ordemServico.concluir();

        return ordemServicoRepository.save(ordemServico);
    }

    /*
     * CONSULTAS
     */

    @Transactional(readOnly = true)
    public List<OrdemServico> buscarPorEquipamento(
            Long equipamentoId) {

        return ordemServicoRepository
                .findByEquipamentoId(equipamentoId);
    }

    @Transactional(readOnly = true)
    public List<OrdemServico> buscarPorResponsavel(
            Long responsavelId) {

        return ordemServicoRepository
                .findByResponsavelId(responsavelId);
    }

    @Transactional(readOnly = true)
    public List<OrdemServico> buscarPorStatus(
            String status) {

        return ordemServicoRepository
                .findByStatus(status);
    }

}
