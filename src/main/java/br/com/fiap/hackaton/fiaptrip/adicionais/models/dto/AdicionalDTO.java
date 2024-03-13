package br.com.fiap.hackaton.fiaptrip.adicionais.models.dto;

public record AdicionalDTO(
        Long id,
        String descricao,
        Double valor,
        String tipoAdicional
) {
}
