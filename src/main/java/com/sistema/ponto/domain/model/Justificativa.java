package com.sistema.ponto.domain.model;

/**
 * Modelo de domínio puro que representa uma Justificativa de ponto.
 * Livre de qualquer anotação de framework.
 * <p>
 * Pacote: domain.model
 */
public class Justificativa {

    private final Long id;
    private final Long colaboradorId;
    private final Long registroPontoId; // pode ser null (justificativa não vinculada a batida)
    private final String texto;
    private final StatusJustificativa status;

    public Justificativa(Long id, Long colaboradorId, Long registroPontoId,
                         String texto, StatusJustificativa status) {
        if (colaboradorId == null) {
            throw new IllegalArgumentException("Colaborador é obrigatório para criar uma justificativa.");
        }
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Texto da justificativa não pode ser nulo ou vazio.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status da justificativa é obrigatório.");
        }
        this.id = id;
        this.colaboradorId = colaboradorId;
        this.registroPontoId = registroPontoId;
        this.texto = texto;
        this.status = status;
    }

    /**
     * Factory method para criação de nova justificativa com status PENDENTE.
     */
    public static Justificativa criarNova(Long colaboradorId, Long registroPontoId, String texto) {
        return new Justificativa(null, colaboradorId, registroPontoId, texto, StatusJustificativa.PENDENTE);
    }

    public Long getId() {
        return id;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public Long getRegistroPontoId() {
        return registroPontoId;
    }

    public String getTexto() {
        return texto;
    }

    public StatusJustificativa getStatus() {
        return status;
    }
}
