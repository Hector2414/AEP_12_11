package com.cesumar.metria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens_servico")

public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataEncerramento;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "equipamento_id",
            nullable = false
    )
    private Equipamento equipamento;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "responsavel_id",
            nullable = false
    )
    private Funcionario responsavel;

    @OneToMany(
            mappedBy = "ordemServico",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemOrdemServico> itens = new ArrayList<>();

    public OrdemServico() {
    }

    public void abrir() {

        if (this.equipamento == null) {
            throw new IllegalStateException(
                    "Uma OS precisa possuir um equipamento"
            );
        }

        if (this.responsavel == null) {
            throw new IllegalStateException(
                    "Uma OS precisa possuir um responsável"
            );
        }

        this.dataAbertura = LocalDateTime.now();
        this.status = "ABERTA";
    }

    public void concluir() {

        if ("ENCERRADA".equals(this.status)) {
            throw new IllegalStateException(
                    "A Ordem de Serviço já está encerrada"
            );
        }

        this.status = "ENCERRADA";
        this.dataEncerramento = LocalDateTime.now();
    }

    public void adicionarItem(ItemOrdemServico item) {
        item.setOrdemServico(this);
        this.itens.add(item);
    }

    public void removerItem(ItemOrdemServico item) {
        this.itens.remove(item);
        item.setOrdemServico(null);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public List<ItemOrdemServico> getItens() {
        return itens;
    }
}
