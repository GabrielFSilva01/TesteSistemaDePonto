package com.sistema.ponto.infrastructure.persistence.entity;

/**
 * Enum que define os possíveis status de uma justificativa de ponto.
 * Utilizado exclusivamente na camada de infraestrutura/persistência JPA.
 */
public enum StatusJustificativa {
    PENDENTE,
    APROVADA,
    REJEITADA
}
