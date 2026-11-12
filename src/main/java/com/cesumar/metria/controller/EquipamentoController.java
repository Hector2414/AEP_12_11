package com.cesumar.metria.controller;


import com.cesumar.metria.model.Equipamento;
import com.cesumar.metria.service.EquipamentoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(
            EquipamentoService equipamentoService) {

        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
            @Valid @RequestBody Equipamento equipamento) {

        Equipamento novoEquipamento =
                equipamentoService.cadastrar(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoEquipamento);
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {

        return ResponseEntity.ok(
                equipamentoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                equipamentoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        equipamentoService.atualizarStatus(id, status);

        return ResponseEntity.noContent().build();
    }

}
