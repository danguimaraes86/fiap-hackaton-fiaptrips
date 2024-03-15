package br.com.fiap.hackaton.fiaptrip.quartos.adjs;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MetodosAuxiliaresTestes {

    public static Localidade getLocalidadeMock() {
        return new Localidade(
                new Random().nextLong(),
                "Localidade Teste",
                "Rua teste abençoado, 25 - São Paulo",
                new HashSet<>(List.of(
                        new Torre(),
                        new Torre()
                ))
        );
    }
    public static Quarto getQuartoMock() {
        return new Quarto(
                TipoQuarto.LUXO,
                new ArrayList<>(List.of(Amenidades.TV))
        );
    }

    public static Torre getTorreMock() {
        return new Torre(
                7L,
                "Torre Teste",
                getLocalidadeMock(),
                new ArrayList<>(List.of(
                        getQuartoMock(),
                        getQuartoMock()
                )));
    }
}
