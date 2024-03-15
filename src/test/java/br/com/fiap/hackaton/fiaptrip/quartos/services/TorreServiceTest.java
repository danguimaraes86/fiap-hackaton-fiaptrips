package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.TorreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getTorreMock;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

public class TorreServiceTest {

    private AutoCloseable mocks;
    private TorreService torreService;
    @Mock
    private TorreRepository torreRepository;

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        torreService = new TorreService(torreRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarTorres {

        @Test
        void deveBuscarTorres_retornaPageableVazio() {
            Page<Torre> torresMock = new PageImpl<>(Collections.emptyList());
            when(torreRepository.findAll(any(Pageable.class))).thenReturn(torresMock);

            Page<Torre> torresFound = torreService.findAll(Pageable.unpaged());
            verify(torreRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(torresFound).isInstanceOf(Page.class);
            assertThat(torresFound.getContent()).isEmpty();
        }

        @Test
        void deveBuscarTorres_retornaPageable() {
            Page<Torre> torresMock = new PageImpl<>(List.of(
                    mock(Torre.class),
                    mock(Torre.class),
                    mock(Torre.class)));
            when(torreRepository.findAll(any(Pageable.class))).thenReturn(torresMock);

            Page<Torre> torresFound = torreService.findAll(Pageable.unpaged());
            verify(torreRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(torresFound).isInstanceOf(Page.class);
            assertThat(torresFound.getContent()).isNotEmpty().hasSize(torresMock.getSize());
        }

        @Test
        void deveBuscarTorresById() {
            Torre torreMock = getTorreMock();
            Long torreID = torreMock.getId();
            when(torreRepository.findById(anyLong()))
                    .thenReturn(Optional.of(torreMock));

            Torre torreFound = torreService.findByID(torreID);
            verify(torreRepository, times(1)).findById(torreID);

            assertThat(torreFound).isNotNull().isEqualTo(torreMock);
            assertThat(torreFound.getId()).isEqualTo(torreMock.getId());
            assertThat(torreFound.getLocalidade()).isEqualTo(torreMock.getLocalidade());
            assertThat(torreFound.getQuartos()).isEqualTo(torreMock.getQuartos());
        }
    }

    @Nested
    class InserirTorres {

        @Test
        void deveInserirTorres() {
            Torre torreMock = getTorreMock();
            when(torreRepository.save(any(Torre.class))).thenReturn(torreMock);

            Torre torreCreated = torreService.createTorre(torreMock);
            verify(torreRepository, times(1)).save(any(Torre.class));

            assertThat(torreCreated).isNotNull().isEqualTo(torreMock);
            assertThat(torreCreated.getId()).isEqualTo(torreMock.getId());
            assertThat(torreCreated.getLocalidade()).isEqualTo(torreMock.getLocalidade());
            assertThat(torreCreated.getQuartos()).isEqualTo(torreMock.getQuartos());
        }
    }

    @Nested
    class AtualizarTorres {

        @Test
        void deveAtualizarTorres() {
            Torre torreMock = getTorreMock();
            Long torreID = torreMock.getId();
            when(torreRepository.findById(anyLong())).thenReturn(Optional.of(torreMock));
            when(torreRepository.save(any(Torre.class))).thenReturn(torreMock);

            Torre torreUpdated = torreService.updateTorre(torreID, torreMock);
            verify(torreRepository, times(1)).findById(torreID);
            verify(torreRepository, times(1)).save(any(Torre.class));

            assertThat(torreUpdated).isNotNull().isEqualTo(torreMock);
            assertThat(torreUpdated.getId()).isEqualTo(torreMock.getId());
            assertThat(torreUpdated.getLocalidade()).isEqualTo(torreMock.getLocalidade());
            assertThat(torreUpdated.getQuartos()).isEqualTo(torreMock.getQuartos());
        }
    }

    @Nested
    class DeletarTorres {

        @Test
        void deveDeletarTorre() {
            Torre torreMock = getTorreMock();
            Long torreId = torreMock.getId();
            when(torreRepository.findById(anyLong())).thenReturn(Optional.of(torreMock));
            doNothing().when(torreRepository).delete(any(Torre.class));

            torreService.deleteByID(torreId);
            verify(torreRepository, times(1)).findById(torreId);
            verify(torreRepository, times(1)).delete(any(Torre.class));
        }
    }

    @Nested
    class Exceptions {

//        @Test
//        void deveLancarExcecao_buscarClientePorEmail_emailNaoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            String clienteEmail = clienteMock.getEmail();
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.findClienteByEmail(clienteEmail))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_email [%s] não encontrado", clienteEmail));
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//        }

//        @Test
//        void deveLancarExcecao_inserirCliente_emailJaCadastrado() {
//            Cliente clienteMock = getClienteMock();
//            ClienteDTO clienteDTO = getClienteDtoMock();
//            String clienteEmail = clienteMock.getEmail();
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.of(clienteMock));
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage(format("cliente_email [%s] já cadastrado", clienteEmail));
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }

//        @Test
//        void deveLancarExcecao_inserirCliente_brasileiroSemCpf() {
//            ClienteDTO clienteDTO = new ClienteDTO(
//                    null, "teste", "pt_br",
//                    "2020-01-01", null, null,
//                    "99-9999-8888", "teste@teste.com", "endereco"
//            );
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("cpf não pode estar vazio para clientes brasileiros");
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }

//        @Test
//        void deveLancarExcecao_inserirCliente_estrangeiroSemPassaporte() {
//            ClienteDTO clienteDTO = new ClienteDTO(
//                    null, "teste", "en-US",
//                    "2020-01-01", null, null,
//                    "99-9999-8888", "teste@teste.com", "endereco"
//            );
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("passaporte não pode estar vazio para clientes estrangeiros");
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }

//        @Test
//        void deveLancarExcecao_alterarCliente_naoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            ClienteDTO clienteDTO = clienteMock.toClienteDTO();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.updateCliente(clienteId, clienteDTO))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
//            verify(clienteRepository, times(1)).findById(anyLong());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }

//        @Test
//        void deveLancarExcecao_deletarCliente_naoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.deleteCliente(clienteId))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
//            verify(clienteRepository, times(1)).findById(anyLong());
//            verify(clienteRepository, never()).delete(any(Cliente.class));
//        }
    }
}
