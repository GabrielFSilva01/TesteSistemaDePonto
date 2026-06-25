package com.sistema.ponto.domain.model;

public class Colaborador {
    private final Long id;
    private final String nome;
    private final String email;
    private final String cargo;
    private final boolean ativo;

    public Colaborador(Long id, String nome, String email, String cargo, boolean ativo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do colaborador não pode ser nulo ou vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do colaborador não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
