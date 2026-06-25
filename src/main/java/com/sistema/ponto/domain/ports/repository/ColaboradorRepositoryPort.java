package com.sistema.ponto.domain.ports.repository;

import com.sistema.ponto.domain.model.Colaborador;

import java.util.Optional;

/**
 * Porta de saída (Output Port) para persistência de Colaboradores.
 * Interface pura Java — sem nenhuma anotação de framework.
 * <p>
 * Pacote: domain.ports.repository
 * <p>
 * Implementada pelo adaptador de infraestrutura {@code ColaboradorDatabaseAdapter}.
 */
public interface ColaboradorRepositoryPort {

    Optional<Colaborador> buscarPorId(Long id);

    /**
     * Busca um colaborador pela matrícula.
     * Necessário para o fluxo de autenticação (Login/JWT).
     *
     * @param matricula a matrícula única do colaborador
     * @return Optional contendo o colaborador, ou vazio se não encontrado
     */
    Optional<Colaborador> buscarPorMatricula(String matricula);

    Colaborador salvar(Colaborador colaborador);
}
