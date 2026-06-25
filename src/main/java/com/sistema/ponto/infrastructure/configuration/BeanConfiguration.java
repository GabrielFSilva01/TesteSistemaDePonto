package com.sistema.ponto.infrastructure.configuration;

import com.sistema.ponto.domain.ports.repository.ColaboradorRepositoryPort;
import com.sistema.ponto.domain.ports.repository.RegistroPontoRepositoryPort;
import com.sistema.ponto.domain.ports.service.IpValidatorPort;
import com.sistema.ponto.usecase.RegistrarPontoUseCase;
import com.sistema.ponto.usecase.impl.RegistrarPontoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RegistrarPontoUseCase registrarPontoUseCase(
            ColaboradorRepositoryPort colaboradorRepositoryPort,
            RegistroPontoRepositoryPort registroPontoRepositoryPort,
            IpValidatorPort ipValidatorPort) {
        return new RegistrarPontoUseCaseImpl(
                colaboradorRepositoryPort,
                registroPontoRepositoryPort,
                ipValidatorPort
        );
    }
}
