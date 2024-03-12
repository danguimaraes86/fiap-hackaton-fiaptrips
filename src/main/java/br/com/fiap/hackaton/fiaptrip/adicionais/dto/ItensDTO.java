package br.com.fiap.hackaton.fiaptrip.adicionais.dto;

import br.com.fiap.hackaton.fiaptrip.adicionais.enumerator.ItensEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensDTO {

    private Double valor;

    private String servicoItem;

}
