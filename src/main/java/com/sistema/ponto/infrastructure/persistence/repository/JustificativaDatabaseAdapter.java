package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.domain.model.Justificativa;
import com.sistema.ponto.domain.ports.repository.JustificativaRepositoryPort;
import com.sistema.ponto.infrastructure.mapper.JustificativaMapper;
import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import com.sistema.ponto.infrastructure.persistence.entity.JustificativaEntity;
import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import com.sistema.ponto.infrastructure.persistence.entity.StatusJustificativa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador de banco de dados que implementa a porta de saída {@link JustificativaRepositoryPort}.
 * Injeta internamente as interfaces Spring Data JPA, blindando o domínio de qualquer
 * dependência de framework.
 * <p>
 * Pacote: infrastructure.persistence.repository
 */
@Component
public class JustificativaDatabaseAdapter implements JustificativaRepositoryPort {

    private final JustificativaJpaRepository jpaRepository;
    private final ColaboradorJpaRepository colaboradorJpaRepository;
    private final RegistroPontoJpaRepository registroPontoJpaRepository;

    public JustificativaDatabaseAdapter(JustificativaJpaRepository jpaRepository,
                                        ColaboradorJpaRepository colaboradorJpaRepository,
                                        RegistroPontoJpaRepository registroPontoJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.colaboradorJpaRepository = colaboradorJpaRepository;
        this.registroPontoJpaRepository = registroPontoJpaRepository;
    }

    @Override
    public Justificativa salvar(Justificativa justificativa) {
        // Obtém referências gerenciadas pelo JPA para evitar detached entities
        ColaboradorEntity colaboradorRef = colaboradorJpaRepository
                .getReferenceById(justificativa.getColaboradorId());

        RegistroPontoEntity registroPontoRef = null;
        if (justificativa.getRegistroPontoId() != null) {
            registroPontoRef = registroPontoJpaRepository
                    .getReferenceById(justificativa.getRegistroPontoId());
        }

        JustificativaEntity entity = JustificativaMapper.toEntity(justificativa, colaboradorRef, registroPontoRef);
        JustificativaEntity saved = jpaRepository.save(entity);
        return JustificativaMapper.toDomain(saved);
    }

    @Override
    public Optional<Justificativa> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(JustificativaMapper::toDomain);
    }

    @Override
    public List<Justificativa> buscarPorColaboradorId(Long colaboradorId) {
        return jpaRepository.findAllByColaboradorId(colaboradorId)
                .stream()
                .map(JustificativaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Justificativa> buscarPendentes() {
        return jpaRepository.findAllByStatus(StatusJustificativa.PENDENTE)
                .stream()
                .map(JustificativaMapper::toDomain)
                .toList();
    }
}
