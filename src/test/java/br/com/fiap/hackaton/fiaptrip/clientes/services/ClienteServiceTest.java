package br.com.fiap.hackaton.fiaptrip.clientes.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.clientes.repositories.ClienteRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ClienteServiceTest {

    private static final Faker faker = new Faker(Locale.forLanguageTag("pt_BR"));

    private AutoCloseable mocks;
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    private static Cliente getClienteMock() {
        return new Cliente(
                new Random().nextLong(),
                faker.witcher().witcher(),
                faker.country().countryCode3(),
                faker.date().birthdayLocalDate(),
                faker.cpf().valid(),
                faker.passport().valid(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().emailAddress(),
                faker.address().fullAddress()
        );
    }

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        clienteService = new ClienteService(clienteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarClientes {

        @Test
        void deveBuscarClientes_retornaPageableVazio() {
            Page<Cliente> clientesMock = new PageImpl<>(Collections.emptyList());
            when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientesMock);

            Page<Cliente> clientes = clienteService.findAllClientes(Pageable.unpaged());
            verify(clienteRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(clientes).isInstanceOf(Page.class);
            assertThat(clientes.getContent()).isEmpty();
        }

        @Test
        void deveBuscarClientes_retornaPageable() {
            Page<Cliente> clientesMock = new PageImpl<>(List.of(
                    mock(Cliente.class),
                    mock(Cliente.class),
                    mock(Cliente.class)));
            when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientesMock);

            Page<Cliente> clientes = clienteService.findAllClientes(Pageable.unpaged());
            verify(clienteRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(clientes).isInstanceOf(Page.class);
            assertThat(clientes.getContent()).isNotEmpty().hasSize(clientesMock.getSize());
        }

        @Test
        void deveBuscarClientes_porEmail() {
            Cliente clienteMock = getClienteMock();
            String mockEmail = clienteMock.getEmail();
            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
                    .thenReturn(Optional.of(clienteMock));

            Cliente clienteByEmail = clienteService.findClienteByEmail(mockEmail);
            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(mockEmail);

            assertThat(clienteByEmail).isNotNull().isEqualTo(clienteMock);
            assertThat(clienteByEmail.getId()).isEqualTo(clienteMock.getId());
            assertThat(clienteByEmail.getNomeComleto()).isEqualTo(clienteMock.getNomeComleto());
            assertThat(clienteByEmail.getPaisOrigem()).isEqualTo(clienteMock.getPaisOrigem());
            assertThat(clienteByEmail.getDataNascimento()).isEqualTo(clienteMock.getDataNascimento());
            assertThat(clienteByEmail.getCpf()).isEqualTo(clienteMock.getCpf());
            assertThat(clienteByEmail.getPassaporte()).isEqualTo(clienteMock.getPassaporte());
            assertThat(clienteByEmail.getTelefone()).isEqualTo(clienteMock.getTelefone());
            assertThat(clienteByEmail.getEmail()).isEqualTo(clienteMock.getEmail());
            assertThat(clienteByEmail.getEndereco()).isEqualTo(clienteMock.getEndereco());
        }
    }

    @Nested
    class InserirClientes {

        @Test
        void deveInserirCliente() {
            Cliente clienteMock = getClienteMock();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString())).thenReturn(Optional.empty());

            Cliente cliente = clienteService.createCliente(clienteDTO);
            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(clienteDTO.email());
            verify(clienteRepository, times(1)).save(any(Cliente.class));

            assertThat(cliente).isNotNull();
            assertThat(cliente.getNomeComleto()).isEqualTo(clienteDTO.nomeComleto());
            assertThat(cliente.getPaisOrigem()).isEqualTo(clienteDTO.paisOrigem());
            assertThat(cliente.getDataNascimento()).isEqualTo(clienteDTO.dataNascimento());
            assertThat(cliente.getCpf()).isEqualTo(clienteDTO.cpf().orElse(null));
            assertThat(cliente.getPassaporte()).isEqualTo(clienteDTO.passaporte().orElse(null));
            assertThat(cliente.getTelefone()).isEqualTo(clienteDTO.telefone());
            assertThat(cliente.getEmail()).isEqualTo(clienteDTO.email());
            assertThat(cliente.getEndereco()).isEqualTo(clienteDTO.endereco());
        }
    }

    @Nested
    class AtualizarClientes {

        @Test
        void deveAtualizarCliente() {
            Cliente clienteMock = getClienteMock();
            Long clienteId = clienteMock.getId();
            ClienteDTO clienteAlterado = getClienteMock().convertToDTO();
            when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));
            clienteMock.update(clienteAlterado);
            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);

            Cliente cliente = clienteService.updateCliente(clienteId, clienteAlterado);
            verify(clienteRepository, times(1)).findById(clienteId);
            verify(clienteRepository, times(1)).save(any(Cliente.class));

            assertThat(cliente).isNotNull();
            assertThat(cliente.getNomeComleto()).isEqualTo(clienteAlterado.nomeComleto());
            assertThat(cliente.getPaisOrigem()).isEqualTo(clienteAlterado.paisOrigem());
            assertThat(cliente.getDataNascimento()).isEqualTo(clienteAlterado.dataNascimento());
            assertThat(cliente.getCpf()).isEqualTo(clienteAlterado.cpf().orElse(null));
            assertThat(cliente.getPassaporte()).isEqualTo(clienteAlterado.passaporte().orElse(null));
            assertThat(cliente.getEmail()).isEqualTo(clienteAlterado.email());
            assertThat(cliente.getEndereco()).isEqualTo(clienteAlterado.endereco());
        }
    }

    @Nested
    class DeletarClientes {

        @Test
        void deveDeletarCliente() {
            Cliente clienteMock = getClienteMock();
            Long clienteId = clienteMock.getId();
            when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));
            doNothing().when(clienteRepository).delete(any(Cliente.class));

            clienteService.deleteCliente(clienteId);
            verify(clienteRepository, times(1)).findById(clienteId);
            verify(clienteRepository, times(1)).delete(any(Cliente.class));
        }
    }

    @Nested
    class Exceptions {

        @Test
        void deveLancarExcecao_buscarClientePorEmail_emailNaoEncontrado() {
            Cliente clienteMock = getClienteMock();
            String clienteEmail = clienteMock.getEmail();
            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> clienteService.findClienteByEmail(clienteEmail))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("cliente_email [%s] não encontrado", clienteEmail));
            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
        }

        @Test
        void deveLancarExcecao_inserirCliente_emailJaCadastrado() {
            Cliente clienteMock = getClienteMock();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            String clienteEmail = clienteMock.getEmail();
            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
                    .thenReturn(Optional.of(clienteMock));

            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(format("cliente_email [%s] já cadastrado", clienteEmail));
            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
            verify(clienteRepository, never()).save(any(Cliente.class));
        }

        @Test
        void deveLancarExcecao_alterarCliente_naoEncontrado() {
            Cliente clienteMock = getClienteMock();
            Long clienteId = clienteMock.getId();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> clienteService.updateCliente(clienteId, clienteDTO))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
            verify(clienteRepository, times(1)).findById(anyLong());
            verify(clienteRepository, never()).save(any(Cliente.class));
        }

        @Test
        void deveLancarExcecao_deletarCliente_naoEncontrado() {
            Cliente clienteMock = getClienteMock();
            Long clienteId = clienteMock.getId();
            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> clienteService.deleteCliente(clienteId))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
            verify(clienteRepository, times(1)).findById(anyLong());
            verify(clienteRepository, never()).delete(any(Cliente.class));
        }
    }
}
