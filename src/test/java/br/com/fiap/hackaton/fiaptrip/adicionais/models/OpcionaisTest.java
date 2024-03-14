package br.com.fiap.hackaton.fiaptrip.adicionais.models;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.Opcionais;
import org.junit.jupiter.api.Test;

class OpcionaisTest {

    @Test
    void enumeratorTest(){
        for (Opcionais op : Opcionais.values()) {
            System.out.println(op.getDescricao() + " - " + op.getValor() + " - " + op.getTipoAdicional());
        }
    }
}
