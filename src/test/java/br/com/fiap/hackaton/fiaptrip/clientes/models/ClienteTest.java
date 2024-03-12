package br.com.fiap.hackaton.fiaptrip.clientes.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteTest {

    @Test
    void deveCriarObjeto() {
        Cliente cliente = new Cliente();
        assertThat(cliente).isNotNull().isInstanceOf(Cliente.class);
    }
}
