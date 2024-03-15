package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.QuartoDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.QuartoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getQuartoMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class QuartoServiceTest {

    private AutoCloseable mocks;
    private QuartoService quartoService;
    @Mock
    private QuartoRepository quartoRepository;

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        quartoService = new QuartoService(quartoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarQuartos {

        @Test
        void deveBuscarQuartos_retornaPageableVazio() {
            Page<Quarto> quartosMock = new PageImpl<>(Collections.emptyList());
            when(quartoRepository.findAll(any(Pageable.class))).thenReturn(quartosMock);

            Page<Quarto> quartos = quartoService.findAllQuartos(Pageable.unpaged());
            verify(quartoRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(quartos).isInstanceOf(Page.class);
            assertThat(quartos.getContent()).isEmpty();
        }

        @Test
        void deveBuscarQuartos_retornaPageable() {
            Page<Quarto> quartosMock = new PageImpl<>(List.of(
                    mock(Quarto.class),
                    mock(Quarto.class),
                    mock(Quarto.class)));
            when(quartoRepository.findAll(any(Pageable.class))).thenReturn(quartosMock);

            Page<Quarto> quartos = quartoService.findAllQuartos(Pageable.unpaged());
            verify(quartoRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(quartos).isInstanceOf(Page.class);
            assertThat(quartos.getContent()).isNotEmpty().hasSize(quartosMock.getSize());
        }

        @Test
        void deveBuscarQuarto_porTipo() {
            Quarto quartoMock = getQuartoMock();
            String tipoQuartoString = quartoMock.getTipoQuarto();
            when(quartoRepository.findQuartoByTipoQuartoIgnoreCase(anyString()))
                    .thenReturn(Optional.of(quartoMock));

            Quarto quartoPorTipo = quartoService.findQuartoByTipo(tipoQuartoString);
            verify(quartoRepository, times(1))
                    .findQuartoByTipoQuartoIgnoreCase(any(String.class));

            assertThat(quartoPorTipo).isNotNull().isEqualTo(quartoMock);
            assertThat(quartoPorTipo.getId()).isEqualTo(quartoMock.getId());
            assertThat(quartoPorTipo.getTipoQuarto()).isEqualTo(quartoMock.getTipoQuarto());
            assertThat(quartoPorTipo.getQuantidadePessoas()).isEqualTo(quartoMock.getQuantidadePessoas());
            assertThat(quartoPorTipo.getQuantidadeCamas()).isEqualTo(quartoMock.getQuantidadeCamas());
            assertThat(quartoPorTipo.getAmenidades()).isEqualTo(quartoMock.getAmenidades());
        }
    }

    @Nested
    class InserirQuarto {

        @Test
        void deveInserirQuarto() {
            Quarto quartoMock = getQuartoMock();
            QuartoDTO quartoDTO = new QuartoDTO(TipoQuarto.LUXO, new ArrayList<>(List.of(Amenidades.TV)));
            when(quartoRepository.save(any(Quarto.class))).thenReturn(quartoMock);

            Quarto quartoCreated = quartoService.createNovoQuarto(quartoDTO);
            verify(quartoRepository, times(1)).save(any(Quarto.class));

            assertThat(quartoCreated).isNotNull();
            assertThat(quartoCreated.getId()).isEqualTo(quartoMock.getId());
            assertThat(quartoCreated.getTipoQuarto()).isEqualTo(quartoMock.getTipoQuarto());
            assertThat(quartoCreated.getQuantidadePessoas()).isEqualTo(quartoMock.getQuantidadePessoas());
            assertThat(quartoCreated.getQuantidadeCamas()).isEqualTo(quartoMock.getQuantidadeCamas());
            assertThat(quartoCreated.getAmenidades()).isEqualTo(quartoMock.getAmenidades());
        }
    }

    @Nested
    class AtualizarQuarto {

        @Test
        void deveAtualizarQuarto() {
            Quarto quartoMock = getQuartoMock();
            Long quartoId = 5L;
            QuartoDTO quartoDTO = new QuartoDTO(TipoQuarto.LUXO, new ArrayList<>(List.of(Amenidades.TV)));
            when(quartoRepository.findById(anyLong())).thenReturn(Optional.of(quartoMock));
            when(quartoRepository.save(any(Quarto.class))).thenReturn(quartoMock);

            Quarto quartoUpdated = quartoService.updateQuarto(quartoId, quartoDTO);
            verify(quartoRepository, times(1)).findById(anyLong());
            verify(quartoRepository, times(1)).save(any(Quarto.class));

            assertThat(quartoUpdated).isNotNull();
            assertThat(quartoUpdated.getId()).isEqualTo(quartoMock.getId());
            assertThat(quartoUpdated.getTipoQuarto()).isEqualTo(quartoMock.getTipoQuarto());
            assertThat(quartoUpdated.getQuantidadePessoas()).isEqualTo(quartoMock.getQuantidadePessoas());
            assertThat(quartoUpdated.getQuantidadeCamas()).isEqualTo(quartoMock.getQuantidadeCamas());
            assertThat(quartoUpdated.getAmenidades()).isEqualTo(quartoMock.getAmenidades());
        }
    }

    @Nested
    class DeletarQuarto {

        @Test
        void deveDeletarQuarto() {
            Quarto quartoMock = getQuartoMock();
            Long quartoMockId = 5L;
            when(quartoRepository.findById(anyLong())).thenReturn(Optional.of(quartoMock));
            doNothing().when(quartoRepository).delete(any(Quarto.class));

            quartoService.deleteQuartoById(quartoMockId);
            verify(quartoRepository, times(1)).findById(anyLong());
            verify(quartoRepository, times(1)).delete(any(Quarto.class));
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
