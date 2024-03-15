package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.LocalidadeRepository;
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

import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getLocalidadeMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class LocalidadeServiceTest {

    private AutoCloseable mocks;
    private LocalidadeService localidadeService;
    @Mock
    private LocalidadeRepository localidadeRepository;

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        localidadeService = new LocalidadeService(localidadeRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarLocalidades {

        @Test
        void deveBuscarLocalidades_retornaPageableVazio() {
            Page<Localidade> localidadesMock = new PageImpl<>(Collections.emptyList());
            when(localidadeRepository.findAll(any(Pageable.class))).thenReturn(localidadesMock);

            Page<Localidade> localidades = localidadeService.findAll(Pageable.unpaged());
            verify(localidadeRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(localidades).isInstanceOf(Page.class);
            assertThat(localidades.getContent()).isEmpty();
        }

        @Test
        void deveBuscarLocalidade_retornaListLocalidade() {
            Page<Localidade> localidadesMock = new PageImpl<>(List.of(
                    mock(Localidade.class),
                    mock(Localidade.class),
                    mock(Localidade.class)));
            when(localidadeRepository.findAll(any(Pageable.class))).thenReturn(localidadesMock);

            Page<Localidade> localidades = localidadeService.findAll(Pageable.unpaged());
            verify(localidadeRepository, times(1)).findAll(any(Pageable.class));
            assertThat(localidades).isInstanceOf(Page.class);
            assertThat(localidades.getContent()).isNotEmpty().hasSize(localidadesMock.getSize());
        }

        @Test
        void deveBuscarLocalidadesPorID() {
            Localidade localidadeMock = getLocalidadeMock();
            Long localidadeID = localidadeMock.getId();
            when(localidadeRepository.findById(anyLong()))
                    .thenReturn(Optional.of(localidadeMock));

            Localidade localidadeFound = localidadeService.findById(localidadeID);
            verify(localidadeRepository, times(1))
                    .findById(localidadeID);

            assertThat(localidadeFound).isNotNull().isEqualTo(localidadeMock);
            assertThat(localidadeFound.getId()).isEqualTo(localidadeMock.getId());
            assertThat(localidadeFound.getNome()).isEqualTo(localidadeMock.getNome());
            assertThat(localidadeFound.getEndereco()).isEqualTo(localidadeMock.getEndereco());
            assertThat(localidadeFound.getTorres()).isEqualTo(localidadeMock.getTorres());
        }
    }

    @Nested
    class InserirLocalidade {

        @Test
        void deveInserirLocalidade() {
            Localidade localidadeMock = getLocalidadeMock();
            when(localidadeRepository.save(any(Localidade.class))).thenReturn(localidadeMock);

            Localidade localidadeCreated = localidadeService.createLocalidade(localidadeMock);
            verify(localidadeRepository, times(1)).save(any(Localidade.class));

            assertThat(localidadeCreated).isNotNull().isEqualTo(localidadeMock);
            assertThat(localidadeCreated.getId()).isEqualTo(localidadeMock.getId());
            assertThat(localidadeCreated.getNome()).isEqualTo(localidadeMock.getNome());
            assertThat(localidadeCreated.getEndereco()).isEqualTo(localidadeMock.getEndereco());
            assertThat(localidadeCreated.getTorres()).isEqualTo(localidadeMock.getTorres());
        }
    }

    @Nested
    class AtualizarLocalidade {

        @Test
        void deveAtualizarLocalidade() {
            Localidade localidadeMock = getLocalidadeMock();
            Long localidadeId = localidadeMock.getId();
            when(localidadeRepository.findById(anyLong())).thenReturn(Optional.of(localidadeMock));
            when(localidadeRepository.save(any(Localidade.class))).thenReturn(localidadeMock);

            Localidade localidadeUpdated = localidadeService.updateLocalidade(localidadeId, localidadeMock);
            verify(localidadeRepository, times(1)).findById(anyLong());
            verify(localidadeRepository, times(1)).save(any(Localidade.class));

            assertThat(localidadeUpdated).isNotNull().isEqualTo(localidadeMock);
            assertThat(localidadeUpdated.getId()).isEqualTo(localidadeMock.getId());
            assertThat(localidadeUpdated.getNome()).isEqualTo(localidadeMock.getNome());
            assertThat(localidadeUpdated.getEndereco()).isEqualTo(localidadeMock.getEndereco());
            assertThat(localidadeUpdated.getTorres()).isEqualTo(localidadeMock.getTorres());
        }
    }

    @Nested
    class DeletarClientes {

        @Test
        void deveDeletarCliente() {
            Localidade localidadeMock = getLocalidadeMock();
            Long localidadeId = localidadeMock.getId();
            when(localidadeRepository.findById(anyLong())).thenReturn(Optional.of(localidadeMock));
            doNothing().when(localidadeRepository).delete(any(Localidade.class));

            localidadeService.deleteByID(localidadeId);
            verify(localidadeRepository, times(1)).findById(localidadeId);
            verify(localidadeRepository, times(1)).delete(any(Localidade.class));
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
