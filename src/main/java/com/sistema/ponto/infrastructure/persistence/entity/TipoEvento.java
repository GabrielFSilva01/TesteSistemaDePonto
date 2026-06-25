package com.sistema.ponto.infrastructure.persistence.entity;

/**
 * Enum que define os tipos de evento de batida de ponto.
 * Utilizado exclusivamente na camada de infraestrutura/persistência JPA.
 */
public enum TipoEvento {
    ENTRADA,
    INICIO_PAUSA,
    FIM_PAUSA,
    SAIDA
}
