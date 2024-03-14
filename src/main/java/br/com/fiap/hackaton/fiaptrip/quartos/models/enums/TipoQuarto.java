package br.com.fiap.hackaton.fiaptrip.quartos.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoQuarto {
    STANDARD("Standard", 2L, "1 Cama Queen"),
    LUXO("Luxo", 2L, "1 Cama King"),
    PREMIUM("Premium", 2L, "1 Cama Super King");

    private final String nome;
    private final Long quantidadePessoas;
    private final String quantidadeCamas;
}
