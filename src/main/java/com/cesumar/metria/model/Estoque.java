package com.cesumar.metria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "estoques")

public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(
            name = "peca_id",
            nullable = false,
            unique = true
    )
    private Peca peca;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantidadeDisponivel = 0;

    public Estoque() {
    }

    public boolean estaAbaixoDoMinimo() {
        return quantidadeDisponivel < peca.getEstoqueMinimo();
    }

    public void adicionar(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero"
            );
        }

        this.quantidadeDisponivel += quantidade;
    }

    public void baixar(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero"
            );
        }

        if (quantidade > quantidadeDisponivel) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente"
            );
        }

        this.quantidadeDisponivel -= quantidade;
    }

    public Long getId() {
        return id;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

}
