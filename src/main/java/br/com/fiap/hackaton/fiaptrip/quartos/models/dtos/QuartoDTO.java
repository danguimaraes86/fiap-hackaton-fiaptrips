package br.com.fiap.hackaton.fiaptrip.quartos.models.dtos;

import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;

import java.util.List;

public record QuartoDTO(
        TipoQuarto tipoQuarto,
        List<Amenidades> amenidades
) {
}
