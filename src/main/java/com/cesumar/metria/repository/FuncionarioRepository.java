package com.cesumar.metria.repository;


import com.cesumar.metria.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByMatricula(String matricula);

    Optional<Funcionario> findByEmail(String email);
}
