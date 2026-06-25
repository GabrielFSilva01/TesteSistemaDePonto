package com.sistema.ponto.domain.ports.repository;

import com.sistema.ponto.domain.model.Justificativa;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saída (Output Port) para persistência de Justificativas.
 * Interface pura Java — sem nenhuma anotação de framework.
 * <p>
 * Pacote: domain.ports.repository
 * <p>
 * Implementada pelo adaptador de infraestrutura {@code JustificativaDatabaseAdapter}.
 */
public interface JustificativaRepositoryPort {

    Justificativa salvar(Justificativa justificativa);

    Optional<Justificativa> buscarPorId(Long id);

    List<Justificativa> buscarPorColaboradorId(Long colaboradorId);

    /**
     * Busca todas as justificativas pendentes de aprovação.
     * Utilizado pelo fluxo de administração.
     *
     * @return lista de justificativas com status PENDENTE
     */
    List<Justificativa> buscarPendentes();
}
