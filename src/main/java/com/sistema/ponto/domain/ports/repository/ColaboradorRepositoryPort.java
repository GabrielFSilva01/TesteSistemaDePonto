package com.sistema.ponto.domain.ports.repository;

import com.sistema.ponto.domain.model.Colaborador;
import java.util.Optional;

public interface ColaboradorRepositoryPort {
    Optional<Colaborador> buscarPorId(Long id);
    Colaborador salvar(Colaborador colaborador);
}
