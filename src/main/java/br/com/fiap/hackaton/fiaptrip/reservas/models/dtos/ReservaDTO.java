package br.com.fiap.hackaton.fiaptrip.reservas.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Map;

public record ReservaDTO(
        @NotBlank String clienteEmail,
        @Size(min = 1) List<Long> quartos,
        @NotBlank String dataCheckIn,
        @NotBlank String dataCheckOut,
        Map<String, Long> adicionalList
) {
}
