package br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional.PRODUTO;
import static br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional.SERVICO;

@Getter
@RequiredArgsConstructor
public enum Opcionais {

    CAFE("CAFÉ DA MANHÃ", 65.0, SERVICO),
    ALMOCO("ALMOÇO", 65.0, SERVICO),
    JANTAR("JANTAR", 85.0, SERVICO),
    MASSAGEM("MASSAGEM COMPLETA", 250.0, SERVICO),
    MANICURE("MANICURE", 85.0, SERVICO),
    REFRIGERANTES("REFRIGERANTES", 9.0, PRODUTO),
    SUCOS("SUCOS", 13.0, PRODUTO),
    CERVEJAS_NACIONAIS("CERVEJAS NACIONAIS", 15.0, PRODUTO),
    CERVEJAS_IMPORTADAS("CERVEJAS IMPORTADAS", 25.0, PRODUTO),
    CAIPIRINHAS("CAIPIRINHAS", 35.0, PRODUTO);

    private final String descricao;
    private final Double valor;
    private final TipoAdicional tipoAdicional;
}
