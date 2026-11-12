package com.cesumar.metria.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tecnicos")

public class Tecnico extends Pessoa {

    private String especialidade;

    public Tecnico() {
    }

    @Override
    public String identificar() {
        return "Técnico: " + getNome()
                + " | Especialidade: " + especialidade;
    }

    public String executarOS() {
        return "Técnico executando Ordem de Serviço";
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
