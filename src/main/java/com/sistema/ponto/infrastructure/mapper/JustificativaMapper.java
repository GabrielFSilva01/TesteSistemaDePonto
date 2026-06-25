package com.sistema.ponto.infrastructure.mapper;

import com.sistema.ponto.domain.model.Justificativa;
import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import com.sistema.ponto.infrastructure.persistence.entity.JustificativaEntity;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;

/**
 * Mapper responsável pela conversão entre o modelo de domínio {@link Justificativa}
 * e a entidade JPA de infraestrutura {@link JustificativaEntity}.
 * <p>
 * Pacote: infrastructure.mapper
 */
public class JustificativaMapper {

    public static Justificativa toDomain(JustificativaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Justificativa(
                entity.getId(),
                entity.getColaborador() != null ? entity.getColaborador().getId() : null,
                entity.getRegistroPonto() != null ? entity.getRegistroPonto().getId() : null,
                entity.getTexto(),
                mapStatusToDomain(entity.getStatus())
        );
    }

    public static JustificativaEntity toEntity(Justificativa domain,
                                               ColaboradorEntity colaboradorRef,
                                               RegistroPontoEntity registroPontoRef) {
        if (domain == null) {
            return null;
        }
        return new JustificativaEntity(
                domain.getId(),
                colaboradorRef,
                registroPontoRef,
                domain.getTexto(),
                mapStatusToInfra(domain.getStatus())
        );
    }

    // --- Conversão de Enums: StatusJustificativa (Domínio) ↔ StatusJustificativa (Infraestrutura) ---

    private static com.sistema.ponto.domain.model.StatusJustificativa mapStatusToDomain(
            com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa infraStatus) {
        return switch (infraStatus) {
            case PENDENTE -> com.sistema.ponto.domain.model.StatusJustificativa.PENDENTE;
            case APROVADA -> com.sistema.ponto.domain.model.StatusJustificativa.APROVADA;
            case REJEITADA -> com.sistema.ponto.domain.model.StatusJustificativa.REJEITADA;
        };
    }

    private static com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa mapStatusToInfra(
            com.sistema.ponto.domain.model.StatusJustificativa domainStatus) {
        return switch (domainStatus) {
            case PENDENTE -> com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa.PENDENTE;
            case APROVADA -> com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa.APROVADA;
            case REJEITADA -> com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa.REJEITADA;
        };
    }
}
