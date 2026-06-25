package com.sistema.ponto.usecase.dto;

public record RegistrarPontoRequest(
    Long colaboradorId,
    String tipoRegistro,
    String ipOrigem
) {
    public RegistrarPontoRequest {
        if (colaboradorId == null) {
            throw new IllegalArgumentException("ID do colaborador é obrigatório.");
        }
        if (tipoRegistro == null || tipoRegistro.isBlank()) {
            throw new IllegalArgumentException("Tipo de registro é obrigatório.");
        }
        if (ipOrigem == null || ipOrigem.isBlank()) {
            throw new IllegalArgumentException("IP de origem é obrigatório.");
        }
    }
}
