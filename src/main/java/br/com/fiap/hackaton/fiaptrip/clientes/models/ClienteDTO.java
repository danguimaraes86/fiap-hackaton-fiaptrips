package br.com.fiap.hackaton.fiaptrip.clientes.models;

import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        Long id,
        @NotBlank String nomeComleto,
        @NotBlank String paisOrigem,
        @NotBlank String dataNascimento,
        String cpf,
        String passaporte,
        @NotBlank String telefone,
        @NotBlank String email,
        @NotBlank String endereco
) {
}
