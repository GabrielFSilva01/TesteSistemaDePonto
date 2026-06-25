package com.sistema.ponto.infrastructure.controller;

import com.sistema.ponto.usecase.RegistrarPontoUseCase;
import com.sistema.ponto.usecase.dto.RegistrarPontoRequest;
import com.sistema.ponto.usecase.dto.RegistrarPontoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pontos")
@Tag(name = "Controle de Ponto", description = "Endpoints para registro e acompanhamento de ponto digital")
public class RegistroPontoController {

    private final RegistrarPontoUseCase registrarPontoUseCase;

    public RegistroPontoController(RegistrarPontoUseCase registrarPontoUseCase) {
        this.registrarPontoUseCase = registrarPontoUseCase;
    }

    @PostMapping
    @Operation(
        summary = "Registrar Ponto", 
        description = "Registra a batida de ponto (Entrada, Saída, etc.) para o colaborador especificado. " +
                      "O IP de origem é extraído da requisição e validado automaticamente na infraestrutura."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro de ponto efetuado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros de entrada inválidos ou IP de origem bloqueado"),
        @ApiResponse(responseCode = "401", description = "Token JWT não fornecido ou inválido"),
        @ApiResponse(responseCode = "404", description = "Colaborador não encontrado no sistema")
    })
    public ResponseEntity<RegistrarPontoResponse> registrarPonto(
            @RequestBody RegistrarPontoHttpRequest requestBody,
            HttpServletRequest httpServletRequest) {
        
        // Obtém o IP do cliente de forma dinâmica na infraestrutura
        String ipOrigem = httpServletRequest.getRemoteAddr();
        
        // Trata proxy reverso/load balancers se presente no header
        String xForwardedFor = httpServletRequest.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            ipOrigem = xForwardedFor.split(",")[0].trim();
        }

        // Mapeia para a requisição do caso de uso
        RegistrarPontoRequest useCaseRequest = new RegistrarPontoRequest(
                requestBody.colaboradorId(),
                requestBody.tipoRegistro(),
                ipOrigem
        );

        // Executa o caso de uso
        RegistrarPontoResponse response = registrarPontoUseCase.executar(useCaseRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Record de Entrada HTTP isolado para a API
    public record RegistrarPontoHttpRequest(
            Long colaboradorId,
            String tipoRegistro
    ) {}
}
