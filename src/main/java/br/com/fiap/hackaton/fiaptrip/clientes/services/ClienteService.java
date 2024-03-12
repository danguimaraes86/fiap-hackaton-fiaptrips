package br.com.fiap.hackaton.fiaptrip.clientes.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.clientes.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Page<Cliente> findAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente findClienteByEmail(String email) {
        return clienteRepository.findClienteByEmailContainingIgnoreCase(email).orElseThrow(
                () -> new NoSuchElementException(format("cliente_email [%s] não encontrado", email))
        );
    }

    public Cliente createCliente(ClienteDTO novoCliente) {
        clienteRepository.findClienteByEmailContainingIgnoreCase(novoCliente.email()).ifPresent(
                cliente -> {
                    throw new IllegalArgumentException(format("cliente_email [%s] já cadastrado", cliente.getEmail()));
                }
        );

        Cliente cliente = new Cliente(
                novoCliente.nomeComleto(), novoCliente.paisOrigem(), novoCliente.dataNascimento(),
                novoCliente.cpf().orElse(null), novoCliente.passaporte().orElse(null),
                novoCliente.telefone(), novoCliente.email(), novoCliente.endereco()
        );
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new NoSuchElementException(format("cliente_id [%d] não encontrado", clienteId))
        );
        cliente.update(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long clienteId) {
        clienteRepository.delete(
                clienteRepository.findById(clienteId).orElseThrow(
                        () -> new NoSuchElementException(format("cliente_id [%d] não encontrado", clienteId))
                )
        );
    }
}
