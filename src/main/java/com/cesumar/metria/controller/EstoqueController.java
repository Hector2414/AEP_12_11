package com.cesumar.metria.controller;



import com.cesumar.metria.model.Estoque;
import com.cesumar.metria.service.EstoqueService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(
            EstoqueService estoqueService) {

        this.estoqueService = estoqueService;
    }

    /*
     * Listar todos os estoques
     */
    @GetMapping
    public ResponseEntity<List<Estoque>> listar() {

        return ResponseEntity.ok(
                estoqueService.listar()
        );
    }

    /*
     * Buscar estoque de uma peça
     */
    @GetMapping("/peca/{pecaId}")
    public ResponseEntity<Estoque> buscarPorPeca(
            @PathVariable Long pecaId) {

        return ResponseEntity.ok(
                estoqueService.buscarPorPeca(pecaId)
        );
    }

    /*
     * Entrada de estoque
     */
    @PostMapping("/peca/{pecaId}/entrada")
    public ResponseEntity<Estoque> adicionarEstoque(
            @PathVariable Long pecaId,
            @RequestParam Integer quantidade) {

        Estoque estoque =
                estoqueService.adicionarEstoque(
                        pecaId,
                        quantidade
                );

        return ResponseEntity.ok(estoque);
    }

    /*
     * Consultar situação
     */
    @GetMapping("/peca/{pecaId}/situacao")
    public ResponseEntity<Map<String, Object>> situacao(
            @PathVariable Long pecaId) {

        Estoque estoque =
                estoqueService.buscarPorPeca(pecaId);

        boolean alerta =
                estoqueService.verificarAlertaEstoque(pecaId);

        String situacao =
                estoqueService.obterSituacaoEstoque(pecaId);

        Map<String, Object> resposta = Map.of(
                "peca", estoque.getPeca().getNome(),
                "quantidadeDisponivel",
                estoque.getQuantidadeDisponivel(),
                "estoqueMinimo",
                estoque.getPeca().getEstoqueMinimo(),
                "alerta", alerta,
                "situacao", situacao
        );

        return ResponseEntity.ok(resposta);
    }

}
