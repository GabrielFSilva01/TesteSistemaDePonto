package com.sistema.ponto.infrastructure.persistence.repository;

import com.sistema.ponto.infrastructure.persistence.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
}
