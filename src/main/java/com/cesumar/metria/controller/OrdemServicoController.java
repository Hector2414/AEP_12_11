package com.cesumar.metria.controller;


import com.cesumar.metria.model.OrdemServico;
import com.cesumar.metria.service.OrdemServicoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(
            OrdemServicoService ordemServicoService) {

        this.ordemServicoService = ordemServicoService;
    }

    /*
     * Abrir OS
     */
    @PostMapping
    public ResponseEntity<OrdemServico> abrir(
            @RequestParam Long equipamentoId,
            @RequestParam Long responsavelId,
            @RequestParam String tipo) {

        OrdemServico ordemServico =
                ordemServicoService.abrir(
                        equipamentoId,
                        responsavelId,
                        tipo
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordemServico);
    }

    /*
     * Listar OS
     */
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

        return ResponseEntity.ok(
                ordemServicoService.listar()
        );
    }

    /*
     * Buscar OS por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorId(id)
        );
    }

    /*
     * Adicionar peça
     */
    @PostMapping("/{id}/pecas")
    public ResponseEntity<OrdemServico> adicionarPeca(
            @PathVariable Long id,
            @RequestParam Long pecaId,
            @RequestParam Integer quantidade) {

        OrdemServico ordemServico =
                ordemServicoService.adicionarPeca(
                        id,
                        pecaId,
                        quantidade
                );

        return ResponseEntity.ok(ordemServico);
    }

    /*
     * Remover peça
     */
    @DeleteMapping("/{id}/pecas/{itemId}")
    public ResponseEntity<OrdemServico> removerPeca(
            @PathVariable Long id,
            @PathVariable Long itemId) {

        OrdemServico ordemServico =
                ordemServicoService.removerPeca(
                        id,
                        itemId
                );

        return ResponseEntity.ok(ordemServico);
    }

    /*
     * Encerrar OS
     */
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<OrdemServico> encerrar(
            @PathVariable Long id) {

        OrdemServico ordemServico =
                ordemServicoService.encerrar(id);

        return ResponseEntity.ok(ordemServico);
    }

    /*
     * Buscar por equipamento
     */
    @GetMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<List<OrdemServico>> buscarPorEquipamento(
            @PathVariable Long equipamentoId) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorEquipamento(
                        equipamentoId
                )
        );
    }

    /*
     * Buscar por responsável
     */
    @GetMapping("/responsavel/{responsavelId}")
    public ResponseEntity<List<OrdemServico>> buscarPorResponsavel(
            @PathVariable Long responsavelId) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorResponsavel(
                        responsavelId
                )
        );
    }

    /*
     * Buscar por status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdemServico>> buscarPorStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorStatus(status)
        );
    }
}
