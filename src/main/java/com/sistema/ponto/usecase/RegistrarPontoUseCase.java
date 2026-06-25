package com.sistema.ponto.usecase;

import com.sistema.ponto.usecase.dto.RegistrarPontoRequest;
import com.sistema.ponto.usecase.dto.RegistrarPontoResponse;

public interface RegistrarPontoUseCase {
    RegistrarPontoResponse executar(RegistrarPontoRequest request);
}
