package com.cesumar.metria.controller;


import com.cesumar.metria.model.Peca;
import com.cesumar.metria.service.PecaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;

    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @PostMapping
    public ResponseEntity<Peca> cadastrar(
            @Valid @RequestBody Peca peca) {

        Peca novaPeca =
                pecaService.cadastrar(peca);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaPeca);
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listar() {

        return ResponseEntity.ok(
                pecaService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pecaService.buscarPorId(id)
        );
    }

}
