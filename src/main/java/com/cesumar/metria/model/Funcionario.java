package com.cesumar.metria.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
        name = "funcionarios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "matricula"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class Funcionario extends Pessoa {

    @NotBlank(message = "A função é obrigatória")
    private String funcao;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    private String endereco;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Funcionario() {
    }

    @Override
    public String identificar() {
        return "Funcionário: " + getNome()
                + " | Matrícula: " + matricula;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
