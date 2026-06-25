package com.sistema.ponto.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade JPA que mapeia a tabela 'registros_ponto' no banco de dados.
 * Pertence à camada de INFRAESTRUTURA (adaptador de persistência).
 * <p>
 * Pacote: infrastructure.persistence.entity
 * <p>
 * <strong>IMUTABILIDADE:</strong> Esta entidade representa um registro de auditoria.
 * Após persistida, não deve ser alterada. Todos os campos possuem {@code updatable = false}
 * e não há métodos setter expostos (exceto o construtor e o setter de ID usado pelo JPA).
 * <p>
 * Não confundir com o modelo de domínio {@code com.sistema.ponto.domain.model.RegistroPonto}.
 */
@Entity
@Table(name = "registros_ponto")
public class RegistroPontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", nullable = false, updatable = false)
    private ColaboradorEntity colaborador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false, updatable = false, length = 20)
    private TipoEvento tipoEvento;

    @Column(name = "data_hora", nullable = false, updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime timestamp;

    @Column(name = "ip_origem", nullable = false, updatable = false)
    private String ipOrigem;

    // Construtor padrão exigido pelo JPA/Hibernate (protegido para reforçar imutabilidade)
    protected RegistroPontoEntity() {}

    /**
     * Único construtor público para criação do registro.
     * Após construído e persistido, o objeto não deve ser modificado.
     */
    public RegistroPontoEntity(Long id, ColaboradorEntity colaborador,
                               TipoEvento tipoEvento, LocalDateTime timestamp,
                               String ipOrigem) {
        this.id = id;
        this.colaborador = colaborador;
        this.tipoEvento = tipoEvento;
        this.timestamp = timestamp;
        this.ipOrigem = ipOrigem;
    }

    // --- Apenas Getters (sem Setters para garantir imutabilidade pós-persistência) ---

    public Long getId() {
        return id;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }
}
