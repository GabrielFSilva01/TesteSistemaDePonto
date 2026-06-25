package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.infrastructure.persistence.entity.RegistroPontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRegistroPontoRepository extends JpaRepository<RegistroPontoEntity, Long> {
}
