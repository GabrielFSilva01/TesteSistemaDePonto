package com.sistema.ponto.usecase.dto;

import java.time.LocalDateTime;

public record RegistrarPontoResponse(
    Long id,
    Long colaboradorId,
    String nomeColaborador,
    String tipoRegistro,
    LocalDateTime timestamp,
    String ipOrigem
) {}
