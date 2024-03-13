package br.com.fiap.hackaton.fiaptrip.clientes.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.clientes.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.NoSuchElementException;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

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

        LocalDate dataDeNascimento = getDataDeNascimento(novoCliente);
        Locale paisOrigem = getPaisOrigem(novoCliente);

        Cliente cliente = new Cliente(
                novoCliente.nomeComleto(), paisOrigem, dataDeNascimento,
                novoCliente.cpf(), novoCliente.passaporte(),
                novoCliente.telefone(), novoCliente.email(), novoCliente.endereco()
        );
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new NoSuchElementException(format("cliente_id [%d] não encontrado", clienteId))
        );

        LocalDate dataDeNascimento = isEmpty(clienteDTO.dataNascimento()) ? null : getDataDeNascimento(clienteDTO);
        Locale paisOrigem = isEmpty(clienteDTO.paisOrigem()) ? null : getPaisOrigem(clienteDTO);

        cliente.update(clienteDTO, dataDeNascimento, paisOrigem);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long clienteId) {
        clienteRepository.delete(
                clienteRepository.findById(clienteId).orElseThrow(
                        () -> new NoSuchElementException(format("cliente_id [%d] não encontrado", clienteId))
                )
        );
    }

    private LocalDate getDataDeNascimento(ClienteDTO novoCliente) {
        return LocalDate.parse(novoCliente.dataNascimento());
    }

    private Locale getPaisOrigem(ClienteDTO novoCliente) {
        String[] paisOrigemSlipt = novoCliente.paisOrigem().split("[_ -]");
        Locale paisOrigem = new Locale(paisOrigemSlipt[0], paisOrigemSlipt[1]);

        if (paisOrigem.getCountry().equalsIgnoreCase("br") && (isEmpty(novoCliente.cpf()))) {
            throw new IllegalArgumentException("cpf não pode estar vazio para clientes brasileiros");
        }

        if (!paisOrigem.getCountry().equalsIgnoreCase("br") && (isEmpty(novoCliente.passaporte()))) {
            throw new IllegalArgumentException("passaporte não pode estar vazio para clientes estrangeiros");
        }
        return paisOrigem;
    }
}
