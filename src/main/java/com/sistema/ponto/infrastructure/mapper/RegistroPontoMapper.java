package com.sistema.ponto.infrastructure.mapper;

import com.sistema.ponto.domain.model.Colaborador;
import com.sistema.ponto.domain.model.RegistroPonto;
import com.sistema.ponto.domain.model.TipoRegistro;
import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import com.sistema.ponto.infrastructure.persistence.entity.Role;
import com.sistema.ponto.infrastructure.persistence.entity.TipoEvento;

/**
 * Mapper responsável pela conversão entre os modelos de domínio e as entidades JPA de infraestrutura.
 * <p>
 * Inclui a tradução entre os enums de domínio ({@link TipoRegistro}) e de infraestrutura ({@link TipoEvento}),
 * garantindo que a camada de domínio permaneça desacoplada da camada de persistência.
 */
public class RegistroPontoMapper {

    // --- Colaborador ---

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
                null,  // matricula: não gerenciada pelo modelo de domínio atual
                domain.getEmail(),
                null,  // senha: não gerenciada pelo modelo de domínio atual
                domain.getCargo(),
                Role.ROLE_ESTAGIARIO,  // role padrão para mapeamento reverso
                domain.isAtivo()
        );
    }

    // --- RegistroPonto ---

    public static RegistroPonto toDomain(RegistroPontoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RegistroPonto(
                entity.getId(),
                toDomain(entity.getColaborador()),
                mapTipoEventoToDomain(entity.getTipoEvento()),
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
                mapTipoRegistroToInfra(domain.getTipoRegistro()),
                domain.getTimestamp(),
                domain.getIpOrigem()
        );
    }

    // --- Conversão de Enums: TipoRegistro (Domínio) ↔ TipoEvento (Infraestrutura) ---

    /**
     * Converte o enum de infraestrutura ({@link TipoEvento}) para o enum de domínio ({@link TipoRegistro}).
     */
    private static TipoRegistro mapTipoEventoToDomain(TipoEvento tipoEvento) {
        return switch (tipoEvento) {
            case ENTRADA -> TipoRegistro.ENTRADA;
            case INICIO_PAUSA -> TipoRegistro.INTERVALO;
            case FIM_PAUSA -> TipoRegistro.RETORNO;
            case SAIDA -> TipoRegistro.SAIDA;
        };
    }

    /**
     * Converte o enum de domínio ({@link TipoRegistro}) para o enum de infraestrutura ({@link TipoEvento}).
     */
    private static TipoEvento mapTipoRegistroToInfra(TipoRegistro tipoRegistro) {
        return switch (tipoRegistro) {
            case ENTRADA -> TipoEvento.ENTRADA;
            case INTERVALO -> TipoEvento.INICIO_PAUSA;
            case RETORNO -> TipoEvento.FIM_PAUSA;
            case SAIDA -> TipoEvento.SAIDA;
        };
    }
}
