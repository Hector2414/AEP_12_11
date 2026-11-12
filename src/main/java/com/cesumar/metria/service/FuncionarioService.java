package com.cesumar.metria.service;


import com.cesumar.metria.exception.RecursoNaoEncontradoException;
import com.cesumar.metria.exception.RegraDeNegocioException;
import com.cesumar.metria.model.Funcionario;
import com.cesumar.metria.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(
            FuncionarioRepository funcionarioRepository) {

        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Funcionario cadastrar(Funcionario funcionario) {

        funcionarioRepository
                .findByMatricula(funcionario.getMatricula())
                .ifPresent(f -> {
                    throw new RegraDeNegocioException(
                            "Já existe um funcionário com esta matrícula."
                    );
                });

        funcionarioRepository
                .findByEmail(funcionario.getEmail())
                .ifPresent(f -> {
                    throw new RegraDeNegocioException(
                            "Já existe um funcionário com este e-mail."
                    );
                });

        if (funcionario.getAtivo() == null) {
            funcionario.setAtivo(true);
        }

        return funcionarioRepository.save(funcionario);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {

        return funcionarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Funcionário não encontrado."
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public Funcionario desativar(Long id) {

        Funcionario funcionario = buscarPorId(id);

        funcionario.setAtivo(false);

        return funcionarioRepository.save(funcionario);
    }

    public void validarResponsavelAtivo(Funcionario funcionario) {

        if (!Boolean.TRUE.equals(funcionario.getAtivo())) {
            throw new RegraDeNegocioException(
                    "Funcionário inativo não pode ser responsável por uma nova Ordem de Serviço."
            );
        }
    }
}
