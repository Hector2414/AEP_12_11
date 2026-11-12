package com.cesumar.metria.controller;


import com.cesumar.metria.model.Funcionario;
import com.cesumar.metria.service.FuncionarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(
            FuncionarioService funcionarioService) {

        this.funcionarioService = funcionarioService;
    }

    /*
     * POST
     * Cadastrar funcionário
     */
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(
            @Valid @RequestBody Funcionario funcionario) {

        Funcionario novoFuncionario =
                funcionarioService.cadastrar(funcionario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoFuncionario);
    }

    /*
     * GET
     * Listar funcionários
     */
    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {

        return ResponseEntity.ok(
                funcionarioService.listar()
        );
    }

    /*
     * GET
     * Buscar funcionário por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                funcionarioService.buscarPorId(id)
        );
    }

    /*
     * PUT
     * Desativar funcionário
     */
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Funcionario> desativar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                funcionarioService.desativar(id)
        );
    }

}
