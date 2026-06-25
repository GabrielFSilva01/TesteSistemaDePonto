package com.sistema.ponto.usecase.impl;

import com.sistema.ponto.domain.exception.ColaboradorNaoEncontradoException;
import com.sistema.ponto.domain.exception.DomainException;
import com.sistema.ponto.domain.exception.IpInvalidoException;
import com.sistema.ponto.domain.model.Colaborador;
import com.sistema.ponto.domain.model.RegistroPonto;
import com.sistema.ponto.domain.model.TipoRegistro;
import com.sistema.ponto.domain.ports.repository.ColaboradorRepositoryPort;
import com.sistema.ponto.domain.ports.repository.RegistroPontoRepositoryPort;
import com.sistema.ponto.domain.ports.service.IpValidatorPort;
import com.sistema.ponto.usecase.RegistrarPontoUseCase;
import com.sistema.ponto.usecase.dto.RegistrarPontoRequest;
import com.sistema.ponto.usecase.dto.RegistrarPontoResponse;

public class RegistrarPontoUseCaseImpl implements RegistrarPontoUseCase {

    private final ColaboradorRepositoryPort colaboradorRepositoryPort;
    private final RegistroPontoRepositoryPort registroPontoRepositoryPort;
    private final IpValidatorPort ipValidatorPort;

    public RegistrarPontoUseCaseImpl(
            ColaboradorRepositoryPort colaboradorRepositoryPort,
            RegistroPontoRepositoryPort registroPontoRepositoryPort,
            IpValidatorPort ipValidatorPort) {
        this.colaboradorRepositoryPort = colaboradorRepositoryPort;
        this.registroPontoRepositoryPort = registroPontoRepositoryPort;
        this.ipValidatorPort = ipValidatorPort;
    }

    @Override
    public RegistrarPontoResponse executar(RegistrarPontoRequest request) {
        // 1. Validação de IP de rede via porta de serviço
        if (!ipValidatorPort.validar(request.ipOrigem())) {
            throw new IpInvalidoException(request.ipOrigem());
        }

        // 2. Busca do colaborador/estagiário
        Colaborador colaborador = colaboradorRepositoryPort.buscarPorId(request.colaboradorId())
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(request.colaboradorId()));

        // 3. Validação do status do colaborador
        if (!colaborador.isAtivo()) {
            throw new DomainException("Colaborador está inativo e não pode registrar ponto.");
        }

        // 4. Conversão do Tipo de Registro
        TipoRegistro tipoRegistro;
        try {
            tipoRegistro = TipoRegistro.valueOf(request.tipoRegistro().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DomainException("Tipo de registro de ponto inválido. Valores aceitos: ENTRADA, INTERVALO, RETORNO, SAIDA.");
        }

        // 5. Instanciação da entidade de domínio pura e imutável
        RegistroPonto novoRegistro = RegistroPonto.criarNovo(colaborador, tipoRegistro, request.ipOrigem());

        // 6. Salvamento através do adaptador de persistência (saída)
        RegistroPonto registroSalvo = registroPontoRepositoryPort.salvar(novoRegistro);

        // 7. Mapeamento para o DTO de retorno
        return new RegistrarPontoResponse(
                registroSalvo.getId(),
                colaborador.getId(),
                colaborador.getNome(),
                registroSalvo.getTipoRegistro().name(),
                registroSalvo.getTimestamp(),
                registroSalvo.getIpOrigem()
        );
    }
}
