package br.com.fiap.hackaton.fiaptrip.adicionais.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServicosEnum {

    CAFE(65.0, "CAFÉ DA MANHÃ"),
    ALMOCO(65.0, "ALMOÇO"),
    JANTAR(85.0, "JANTAR"),
    MASSAGEM(250.0, "MASSAGEM COMPLETA"),
    MANICURE(85.0, "MANICURE");

    private Double valor;
    private String servico;

}
