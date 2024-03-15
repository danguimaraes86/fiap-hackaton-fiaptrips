package br.com.fiap.hackaton.fiaptrip.utilitarios;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional;
import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import br.com.fiap.hackaton.fiaptrip.reservas.models.dtos.ReservaDTO;

import java.time.LocalDate;
import java.util.*;

public class Generator {

    public static Cliente gerarClienteMock() {
        return new Cliente(
                new Random().nextLong(),
                "nome completo",
                new Locale("pt", "br"),
                LocalDate.now(),
                "CPF",
                "passaporte",
                "telefone",
                "email",
                "endereco"
        );
    }

    public static ClienteDTO gerarClienteDtoMock() {
        return new ClienteDTO(
                new Random().nextLong(),
                "nome completo",
                "pt_br",
                LocalDate.now().toString(),
                "CPF",
                "passaporte",
                "telefone",
                "email",
                "endereco"
        );
    }

    public static Adicional gerarAdicionalMock() {
        return new Adicional(new Random().nextLong(), "Novo adicional", 50.0, TipoAdicional.PRODUTO);
    }

    public static AdicionalDTO gerarAdicionalDTOMock() {
        return new AdicionalDTO(new Random().nextLong(), "Novo adicionalDTO", 20.0, "PRODUTO");
    }

    public static Reserva gerarReservaMock() {
        return new Reserva(
                UUID.randomUUID(),
                gerarClienteMock(),
                List.of(new Quarto()),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                new HashMap<>()
        );
    }

    public static ReservaDTO gerarReservaDTOMock() {
        return new ReservaDTO(
                "email aleatorio",
                new ArrayList<>(Collections.singleton(1L)),
                LocalDate.now().toString(),
                LocalDate.now().plusDays(5).toString(),
                new HashMap<>()
        );
    }
}
