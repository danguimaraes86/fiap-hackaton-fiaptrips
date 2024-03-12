package br.com.fiap.hackaton.fiaptrip.adicionais.enumerator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItensEnum {

    REFRIGERANTES(9.0, "REFRIGERANTES"),
    SUCOS(13.0, "SUCOS"),
    CERVEJAS_NACIONAIS(15.0, "CERVEJAS NACIONAIS"),
    CERVEJAS_IMPORTADAS(25.0, "CERVEJAS IMPORTADAS"),
    CAIPIRINHAS(35.0, "CAIPIRINHAS");

    private Double valor;
    private String item;

}
