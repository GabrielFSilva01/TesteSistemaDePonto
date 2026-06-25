package com.sistema.ponto.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_ponto")
public class RegistroPontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", nullable = false, updatable = false)
    private ColaboradorEntity colaborador;

    @Column(name = "tipo_registro", nullable = false, updatable = false)
    private String tipoRegistro;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "ip_origem", nullable = false, updatable = false)
    private String ipOrigem;

    public RegistroPontoEntity() {}

    public RegistroPontoEntity(Long id, ColaboradorEntity colaborador, String tipoRegistro, LocalDateTime timestamp, String ipOrigem) {
        this.id = id;
        this.colaborador = colaborador;
        this.tipoRegistro = tipoRegistro;
        this.timestamp = timestamp;
        this.ipOrigem = ipOrigem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }
}
