package com.sistema.ponto.domain.model;

import java.time.LocalDateTime;

public class RegistroPonto {
    private final Long id;
    private final Colaborador colaborador;
    private final TipoRegistro tipoRegistro;
    private final LocalDateTime timestamp;
    private final String ipOrigem;

    public RegistroPonto(Long id, Colaborador colaborador, TipoRegistro tipoRegistro, LocalDateTime timestamp, String ipOrigem) {
        if (colaborador == null) {
            throw new IllegalArgumentException("Colaborador é obrigatório para registrar o ponto.");
        }
        if (tipoRegistro == null) {
            throw new IllegalArgumentException("Tipo de registro de ponto é obrigatório.");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("Data/hora do registro de ponto é obrigatória.");
        }
        if (ipOrigem == null || ipOrigem.isBlank()) {
            throw new IllegalArgumentException("IP de origem é obrigatório.");
        }
        this.id = id;
        this.colaborador = colaborador;
        this.tipoRegistro = tipoRegistro;
        this.timestamp = timestamp;
        this.ipOrigem = ipOrigem;
    }

    // Factory method para criação de novos registros (sem ID persistido ainda)
    public static RegistroPonto criarNovo(Colaborador colaborador, TipoRegistro tipoRegistro, String ipOrigem) {
        return new RegistroPonto(null, colaborador, tipoRegistro, LocalDateTime.now(), ipOrigem);
    }

    public Long getId() {
        return id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }
}
