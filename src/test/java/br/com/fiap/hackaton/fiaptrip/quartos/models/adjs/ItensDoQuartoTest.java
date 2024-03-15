package br.com.fiap.hackaton.fiaptrip.quartos.models.adjs;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItensDoQuartoTest {

    @Test
    void testEnumValues() {
        assertEquals(8, ItemsDoQuarto.values().length);

        assertEquals(ItemsDoQuarto.TV, ItemsDoQuarto.valueOf("TV"));
        assertEquals(ItemsDoQuarto.ARCONDICIONADO, ItemsDoQuarto.valueOf("ARCONDICIONADO"));
        assertEquals(ItemsDoQuarto.FRIGOBAR, ItemsDoQuarto.valueOf("FRIGOBAR"));
        assertEquals(ItemsDoQuarto.SOFA, ItemsDoQuarto.valueOf("SOFA"));
        assertEquals(ItemsDoQuarto.POLTRONA, ItemsDoQuarto.valueOf("POLTRONA"));
        assertEquals(ItemsDoQuarto.BANHEIRA, ItemsDoQuarto.valueOf("BANHEIRA"));
        assertEquals(ItemsDoQuarto.MESAESCRITORIO, ItemsDoQuarto.valueOf("MESAESCRITORIO"));
        assertEquals(ItemsDoQuarto.VARANDA, ItemsDoQuarto.valueOf("VARANDA"));
    }
}
