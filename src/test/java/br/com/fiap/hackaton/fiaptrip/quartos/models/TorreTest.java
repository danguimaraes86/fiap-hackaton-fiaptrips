package br.com.fiap.hackaton.fiaptrip.quartos.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TorreTest {

    @Test
    void deveCriarObjeto() {
        Torre torre = new Torre();
        assertThat(torre).isNotNull().isInstanceOf(Torre.class);
    }
}
