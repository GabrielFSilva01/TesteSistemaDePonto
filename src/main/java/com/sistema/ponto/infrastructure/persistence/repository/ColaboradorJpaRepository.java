package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface Spring Data JPA para acesso à tabela 'colaboradores'.
 * Pertence à camada de INFRAESTRUTURA.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Repository
public interface ColaboradorJpaRepository extends JpaRepository<ColaboradorEntity, Long> {

    /**
     * Busca um colaborador pela matrícula.
     * Utilizado no fluxo de autenticação (Login/JWT).
     *
     * @param matricula a matrícula única do colaborador
     * @return Optional contendo o colaborador, ou vazio se não encontrado
     */
    Optional<ColaboradorEntity> findByMatricula(String matricula);
}
