package com.sistema.ponto.domain.exception;

public class ColaboradorNaoEncontradoException extends DomainException {
    public ColaboradorNaoEncontradoException(Long id) {
        super(String.format("Colaborador com ID %d não foi encontrado.", id));
    }
}
