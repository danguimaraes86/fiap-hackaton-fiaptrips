package br.com.fiap.hackaton.fiaptrip.adicionais.dto;

import br.com.fiap.hackaton.fiaptrip.adicionais.enumerator.ServicosEnum;
import jakarta.validation.constraints.NotBlank;

public record ServicosDTO (

        Long id,

        @NotBlank Double valor,

        @NotBlank String servicoItem,

        @NotBlank ServicosEnum servicosEnum

        ){

}
