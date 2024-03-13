package br.com.fiap.hackaton.fiaptrip.utilitarios;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class Generator {

    public static Cliente getClienteMock() {
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

    public static ClienteDTO getClienteDtoMock() {
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
}
