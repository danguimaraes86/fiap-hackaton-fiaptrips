package br.com.fiap.hackaton.fiaptrip.clientes.models;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Optional;

public record ClienteDTO(
        Long id,
        @NotBlank String nomeComleto,
        @NotBlank String paisOrigem,
        @NotBlank LocalDate dataNascimento,
        @NotBlank Optional<String> cpf,
        @NotBlank Optional<String> passaporte,
        @NotBlank String telefone,
        @NotBlank String email,
        @NotBlank String endereco
) {
}
