package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.infrastructure.persistence.entity.JustificativaEntity;
import com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Spring Data JPA para acesso à tabela 'justificativas'.
 * Pertence à camada de INFRAESTRUTURA.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Repository
public interface JustificativaJpaRepository extends JpaRepository<JustificativaEntity, Long> {

    /**
     * Busca todas as justificativas de um colaborador específico.
     *
     * @param colaboradorId ID do colaborador
     * @return lista de justificativas do colaborador
     */
    List<JustificativaEntity> findAllByColaboradorId(Long colaboradorId);

    /**
     * Busca todas as justificativas com um determinado status.
     * Útil para o admin listar as justificativas pendentes de aprovação.
     *
     * @param status o status desejado (PENDENTE, APROVADA, REJEITADA)
     * @return lista de justificativas filtradas por status
     */
    List<JustificativaEntity> findAllByStatus(StatusJustificativa status);
}
