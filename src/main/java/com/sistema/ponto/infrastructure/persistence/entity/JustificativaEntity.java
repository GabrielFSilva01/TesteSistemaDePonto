package com.sistema.ponto.infrastructure.persistence.entity;

import jakarta.persistence.*;

/**
 * Entidade JPA que mapeia a tabela 'justificativas' no banco de dados.
 * Pertence à camada de INFRAESTRUTURA (adaptador de persistência).
 * <p>
 * Pacote: infrastructure.persistence.entity
 * <p>
 * Representa uma justificativa enviada por um colaborador, opcionalmente
 * vinculada a um registro de ponto específico.
 */
@Entity
@Table(name = "justificativas")
public class JustificativaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradorEntity colaborador;

    /**
     * Relacionamento opcional com um registro de ponto.
     * Pode ser {@code null} caso a justificativa não esteja vinculada a nenhuma batida específica.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_ponto_id", nullable = true)
    private RegistroPontoEntity registroPonto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusJustificativa status;

    // Construtor padrão exigido pelo JPA/Hibernate
    public JustificativaEntity() {}

    public JustificativaEntity(Long id, ColaboradorEntity colaborador,
                               RegistroPontoEntity registroPonto,
                               String texto, StatusJustificativa status) {
        this.id = id;
        this.colaborador = colaborador;
        this.registroPonto = registroPonto;
        this.texto = texto;
        this.status = status;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorEntity colaborador) {
        this.colaborador = colaborador;
    }

    public RegistroPontoEntity getRegistroPonto() {
        return registroPonto;
    }

    public void setRegistroPonto(RegistroPontoEntity registroPonto) {
        this.registroPonto = registroPonto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public StatusJustificativa getStatus() {
        return status;
    }

    public void setStatus(StatusJustificativa status) {
        this.status = status;
    }
}
