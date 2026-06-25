package com.sistema.ponto.domain.ports.repository;

import com.sistema.ponto.domain.model.RegistroPonto;

public interface RegistroPontoRepositoryPort {
    RegistroPonto salvar(RegistroPonto registroPonto);
}
