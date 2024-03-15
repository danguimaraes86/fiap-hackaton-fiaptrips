package br.com.fiap.hackaton.fiaptrip.quartos.models.adjs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CamasTest {

    @Test
    void testEnumValues() {
        assertEquals(5, Camas.values().length);

        assertEquals(Camas.CAMAQUEEN, Camas.valueOf("CAMAQUEEN"));
        assertEquals(Camas.CAMAKING, Camas.valueOf("CAMAKING"));
        assertEquals(Camas.CAMASOLTEIRO, Camas.valueOf("CAMASOLTEIRO"));
        assertEquals(Camas.BELICHE, Camas.valueOf("BELICHE"));
        assertEquals(Camas.TRILICHE, Camas.valueOf("TRILICHE"));

    }
}
