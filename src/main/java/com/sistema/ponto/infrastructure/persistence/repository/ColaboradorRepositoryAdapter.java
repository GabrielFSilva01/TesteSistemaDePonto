package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.domain.model.Colaborador;
import com.sistema.ponto.domain.ports.repository.ColaboradorRepositoryPort;
import com.sistema.ponto.infrastructure.mapper.RegistroPontoMapper;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ColaboradorRepositoryAdapter implements ColaboradorRepositoryPort {

    private final SpringDataColaboradorRepository springRepository;

    public ColaboradorRepositoryAdapter(SpringDataColaboradorRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<Colaborador> buscarPorId(Long id) {
        return springRepository.findById(id)
                .map(RegistroPontoMapper::toDomain);
    }

    @Override
    public Colaborador salvar(Colaborador colaborador) {
        var entity = RegistroPontoMapper.toEntity(colaborador);
        var saved = springRepository.save(entity);
        return RegistroPontoMapper.toDomain(saved);
    }
}
