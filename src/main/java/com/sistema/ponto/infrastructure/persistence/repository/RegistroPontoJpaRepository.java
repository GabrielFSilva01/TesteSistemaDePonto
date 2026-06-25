package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interface Spring Data JPA para acesso à tabela 'registros_ponto'.
 * Pertence à camada de INFRAESTRUTURA.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Repository
public interface RegistroPontoJpaRepository extends JpaRepository<RegistroPontoEntity, Long> {

    /**
     * Busca o último registro de ponto de um colaborador específico no dia atual.
     * Útil para determinar qual será o próximo tipo de evento (ENTRADA, INICIO_PAUSA, FIM_PAUSA, SAIDA).
     * <p>
     * A query busca registros cujo {@code data_hora} esteja entre o início e o fim do dia informado,
     * ordenados de forma decrescente pelo timestamp, retornando apenas o primeiro (mais recente).
     *
     * @param colaboradorId ID do colaborador
     * @param inicioDoDia   início do dia (00:00:00)
     * @param fimDoDia      fim do dia (23:59:59.999999999)
     * @return Optional contendo o registro mais recente do dia, ou vazio
     */
    @Query("""
            SELECT r FROM RegistroPontoEntity r
            WHERE r.colaborador.id = :colaboradorId
              AND r.timestamp BETWEEN :inicioDoDia AND :fimDoDia
            ORDER BY r.timestamp DESC
            LIMIT 1
            """)
    Optional<RegistroPontoEntity> findUltimoRegistroDoDia(
            @Param("colaboradorId") Long colaboradorId,
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia
    );

    /**
     * Busca todos os registros de ponto de um colaborador em um intervalo de datas.
     * Útil para montagem do espelho de ponto diário.
     *
     * @param colaboradorId ID do colaborador
     * @param inicio        data/hora de início do intervalo
     * @param fim           data/hora de fim do intervalo
     * @return lista de registros ordenados por timestamp ascendente
     */
    @Query("""
            SELECT r FROM RegistroPontoEntity r
            WHERE r.colaborador.id = :colaboradorId
              AND r.timestamp BETWEEN :inicio AND :fim
            ORDER BY r.timestamp ASC
            """)
    List<RegistroPontoEntity> findAllByColaboradorIdAndTimestampBetween(
            @Param("colaboradorId") Long colaboradorId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
