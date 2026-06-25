package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.domain.model.RegistroPonto;
import com.sistema.ponto.domain.ports.repository.RegistroPontoRepositoryPort;
import com.sistema.ponto.infrastructure.mapper.RegistroPontoMapper;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de banco de dados que implementa a porta de saída {@link RegistroPontoRepositoryPort}.
 * Injeta internamente as interfaces Spring Data JPA, blindando o domínio de qualquer
 * dependência de framework.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Component
public class RegistroPontoDatabaseAdapter implements RegistroPontoRepositoryPort {

    private final RegistroPontoJpaRepository jpaRepository;
    private final ColaboradorJpaRepository colaboradorJpaRepository;

    public RegistroPontoDatabaseAdapter(RegistroPontoJpaRepository jpaRepository,
                                        ColaboradorJpaRepository colaboradorJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.colaboradorJpaRepository = colaboradorJpaRepository;
    }

    @Override
    public RegistroPonto salvar(RegistroPonto registroPonto) {
        RegistroPontoEntity entity = RegistroPontoMapper.toEntity(registroPonto);

        // Assegura que o colaborador anexado é uma referência gerenciada pelo JPA (proxy)
        if (entity.getColaborador() != null && entity.getColaborador().getId() != null) {
            var colaboradorRef = colaboradorJpaRepository.getReferenceById(entity.getColaborador().getId());
            entity = new RegistroPontoEntity(
                    entity.getId(),
                    colaboradorRef,
                    entity.getTipoEvento(),
                    entity.getTimestamp(),
                    entity.getIpOrigem()
            );
        }

        RegistroPontoEntity savedEntity = jpaRepository.save(entity);
        return RegistroPontoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RegistroPonto> buscarUltimoRegistroDoDia(Long colaboradorId, LocalDate data) {
        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);

        return jpaRepository.findUltimoRegistroDoDia(colaboradorId, inicioDoDia, fimDoDia)
                .map(RegistroPontoMapper::toDomain);
    }

    @Override
    public List<RegistroPonto> buscarPorColaboradorEIntervalo(Long colaboradorId,
                                                              LocalDateTime inicio,
                                                              LocalDateTime fim) {
        return jpaRepository.findAllByColaboradorIdAndTimestampBetween(colaboradorId, inicio, fim)
                .stream()
                .map(RegistroPontoMapper::toDomain)
                .toList();
    }
}
