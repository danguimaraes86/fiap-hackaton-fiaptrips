package br.com.fiap.hackaton.fiaptrip.reservas.models.dtos;

import java.util.List;

public record ReservaDTO(
        String clienteEmail,
        List<Long> quartos,
        String dataCheckIn,
        String dataCheckOut
) {
}
