package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.domain.model.RegistroPonto;
import com.sistema.ponto.domain.ports.repository.RegistroPontoRepositoryPort;
import com.sistema.ponto.infrastructure.mapper.RegistroPontoMapper;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistroPontoRepositoryAdapter implements RegistroPontoRepositoryPort {

    private final SpringDataRegistroPontoRepository springRepository;
    private final SpringDataColaboradorRepository springColaboradorRepository;

    public RegistroPontoRepositoryAdapter(
            SpringDataRegistroPontoRepository springRepository,
            SpringDataColaboradorRepository springColaboradorRepository) {
        this.springRepository = springRepository;
        this.springColaboradorRepository = springColaboradorRepository;
    }

    @Override
    public RegistroPonto salvar(RegistroPonto registroPonto) {
        RegistroPontoEntity entity = RegistroPontoMapper.toEntity(registroPonto);
        
        // Assegura que o colaborador anexado é uma referência existente no banco
        if (entity.getColaborador() != null && entity.getColaborador().getId() != null) {
            var colaboradorRef = springColaboradorRepository.getReferenceById(entity.getColaborador().getId());
            entity = new RegistroPontoEntity(
                    entity.getId(),
                    colaboradorRef,
                    entity.getTipoRegistro(),
                    entity.getTimestamp(),
                    entity.getIpOrigem()
            );
        }

        RegistroPontoEntity savedEntity = springRepository.save(entity);
        return RegistroPontoMapper.toDomain(savedEntity);
    }
}
