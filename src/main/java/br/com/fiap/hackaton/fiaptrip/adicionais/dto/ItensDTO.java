package br.com.fiap.hackaton.fiaptrip.adicionais.dto;

import br.com.fiap.hackaton.fiaptrip.adicionais.enumerator.ItensEnum;
import jakarta.validation.constraints.NotBlank;


public record ItensDTO (


        Long id,

        @NotBlank Double valor,

        @NotBlank String servicoItem,

        @NotBlank ItensEnum itensEnum

        ){

}
