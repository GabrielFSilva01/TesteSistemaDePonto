package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.domain.model.Colaborador;
import com.sistema.ponto.domain.ports.repository.ColaboradorRepositoryPort;
import com.sistema.ponto.infrastructure.mapper.RegistroPontoMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Adaptador de banco de dados que implementa a porta de saída {@link ColaboradorRepositoryPort}.
 * Injeta internamente a interface Spring Data JPA {@link ColaboradorJpaRepository},
 * blindando o domínio de qualquer dependência de framework.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Component
public class ColaboradorDatabaseAdapter implements ColaboradorRepositoryPort {

    private final ColaboradorJpaRepository jpaRepository;

    public ColaboradorDatabaseAdapter(ColaboradorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Colaborador> buscarPorId(Long id) {
        Objects.requireNonNull(id, "ID do colaborador não pode ser nulo.");
        return jpaRepository.findById(id)
                .map(RegistroPontoMapper::toDomain);
    }

    @Override
    public Optional<Colaborador> buscarPorMatricula(String matricula) {
        return jpaRepository.findByMatricula(matricula)
                .map(RegistroPontoMapper::toDomain);
    }

    @Override
    public Colaborador salvar(Colaborador colaborador) {
        var entity = Objects.requireNonNull(
                RegistroPontoMapper.toEntity(colaborador),
                "Falha ao converter Colaborador para entidade JPA."
        );
        var saved = jpaRepository.save(entity);
        return RegistroPontoMapper.toDomain(saved);
    }
}
