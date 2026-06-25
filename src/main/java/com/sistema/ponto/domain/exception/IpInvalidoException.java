package com.sistema.ponto.domain.exception;

public class IpInvalidoException extends DomainException {
    public IpInvalidoException(String ip) {
        super(String.format("Acesso negado: O IP de origem '%s' não pertence à rede corporativa autorizada.", ip));
    }
}
