package br.com.fiap.hackaton.fiaptrip.reservas.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReservaTest {

    @Test
    void deveCriarObjeto() {
        Reserva reserva = new Reserva();
        assertThat(reserva).isNotNull().isInstanceOf(Reserva.class);
    }
}
