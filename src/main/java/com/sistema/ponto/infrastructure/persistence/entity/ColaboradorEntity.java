package com.sistema.ponto.infrastructure.persistence.entity;

import jakarta.persistence.*;

/**
 * Entidade JPA que mapeia a tabela 'colaboradores' no banco de dados.
 * Pertence à camada de INFRAESTRUTURA (adaptador de persistência).
 * <p>
 * Pacote: infrastructure.persistence.entity
 * <p>
 * Não confundir com o modelo de domínio {@code com.sistema.ponto.domain.model.Colaborador},
 * que é um objeto puro de domínio, livre de anotações de framework.
 */
@Entity
@Table(name = "colaboradores")
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cargo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false)
    private boolean ativo;

    // Construtor padrão exigido pelo JPA/Hibernate
    public ColaboradorEntity() {}

    public ColaboradorEntity(Long id, String nome, String matricula, String email,
                             String senha, String cargo, Role role, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.role = role;
        this.ativo = ativo;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
