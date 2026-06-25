package com.sistema.ponto.infrastructure.mapper;

import com.sistema.ponto.domain.model.Colaborador;
import com.sistema.ponto.domain.model.RegistroPonto;
import com.sistema.ponto.domain.model.TipoRegistro;
import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;

public class RegistroPontoMapper {

    public static Colaborador toDomain(ColaboradorEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Colaborador(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getCargo(),
                entity.isAtivo()
        );
    }

    public static ColaboradorEntity toEntity(Colaborador domain) {
        if (domain == null) {
            return null;
        }
        return new ColaboradorEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getCargo(),
                domain.isAtivo()
        );
    }

    public static RegistroPonto toDomain(RegistroPontoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RegistroPonto(
                entity.getId(),
                toDomain(entity.getColaborador()),
                TipoRegistro.valueOf(entity.getTipoRegistro()),
                entity.getTimestamp(),
                entity.getIpOrigem()
        );
    }

    public static RegistroPontoEntity toEntity(RegistroPonto domain) {
        if (domain == null) {
            return null;
        }
        return new RegistroPontoEntity(
                domain.getId(),
                toEntity(domain.getColaborador()),
                domain.getTipoRegistro().name(),
                domain.getTimestamp(),
                domain.getIpOrigem()
        );
    }
}
