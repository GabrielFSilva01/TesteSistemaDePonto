package com.sistema.ponto.infrastructure.validator;

import com.sistema.ponto.domain.ports.service.IpValidatorPort;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class IpValidatorAdapter implements IpValidatorPort {

    // Simulação de subredes corporativas autorizadas (VPN, Rede Local, Localhost)
    private static final List<String> INDERECOS_PERMITIDOS = List.of(
            "127.0.0.1",
            "0:0:0:0:0:0:0:1", // IPv6 localhost
            "192.168.",         // Rede local padrão
            "10."               // VPN padrão / Rede corporativa
    );

    @Override
    public boolean validar(String ip) {
        if (ip == null || ip.isBlank()) {
            return false;
        }
        // Valida se o IP começa com um dos prefixos autorizados
        return INDERECOS_PERMITIDOS.stream().anyMatch(ip::startsWith);
    }
}
