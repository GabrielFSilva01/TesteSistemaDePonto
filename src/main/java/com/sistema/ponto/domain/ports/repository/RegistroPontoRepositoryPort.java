package com.sistema.ponto.domain.ports.repository;

import com.sistema.ponto.domain.model.RegistroPonto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Porta de saída (Output Port) para persistência de Registros de Ponto.
 * Interface pura Java — sem nenhuma anotação de framework.
 * <p>
 * Pacote: domain.ports.repository
 * <p>
 * Implementada pelo adaptador de infraestrutura {@code RegistroPontoDatabaseAdapter}.
 */
public interface RegistroPontoRepositoryPort {

    RegistroPonto salvar(RegistroPonto registroPonto);

    /**
     * Busca o último registro de ponto de um colaborador em um dia específico.
     * Utilizado para determinar o próximo tipo de evento esperado
     * (ENTRADA → INICIO_PAUSA → FIM_PAUSA → SAIDA).
     *
     * @param colaboradorId ID do colaborador
     * @param data          a data do dia a ser consultado
     * @return Optional contendo o registro mais recente do dia, ou vazio
     */
    Optional<RegistroPonto> buscarUltimoRegistroDoDia(Long colaboradorId, LocalDate data);

    /**
     * Busca todos os registros de ponto de um colaborador em um intervalo de datas.
     * Utilizado para montagem do espelho de ponto.
     *
     * @param colaboradorId ID do colaborador
     * @param inicio        data/hora de início do intervalo
     * @param fim           data/hora de fim do intervalo
     * @return lista de registros ordenados por timestamp ascendente
     */
    List<RegistroPonto> buscarPorColaboradorEIntervalo(Long colaboradorId, LocalDateTime inicio, LocalDateTime fim);
}
