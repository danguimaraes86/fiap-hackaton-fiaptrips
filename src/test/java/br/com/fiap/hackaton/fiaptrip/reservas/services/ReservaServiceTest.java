package br.com.fiap.hackaton.fiaptrip.reservas.services;

import br.com.fiap.hackaton.fiaptrip.adicionais.service.AdicionalService;
import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.reservas.services.ReservaService;

import br.com.fiap.hackaton.fiaptrip.clientes.services.ClienteService;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.services.QuartoService;
import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import br.com.fiap.hackaton.fiaptrip.reservas.models.dtos.ReservaDTO;
import br.com.fiap.hackaton.fiaptrip.reservas.repositories.ReservaRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ReservaServiceTest {

    private AutoCloseable mocks;
    private ReservaService reservaService;
    @Mock
    private ClienteService clienteService;
    @Mock
    private QuartoService quartoService;
    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private AdicionalService adicionalService;

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        reservaService = new ReservaService(reservaRepository, clienteService, quartoService, adicionalService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarReservas {

        @Test
        void deveBuscarReservas_retornaPageableVazio() {
            Page<Reserva> reservasMock = new PageImpl<>(Collections.emptyList());
            when(reservaRepository.findAll(any(Pageable.class))).thenReturn(reservasMock);

            Page<Reserva> reservas = reservaService.findAllReservas(Pageable.unpaged());
            verify(reservaRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(reservas).isInstanceOf(Page.class);
            assertThat(reservas.getContent()).isEmpty();
        }

        @Test
        void deveBuscarClientes_retornaPageable() {
            Page<Reserva> reservasMock = new PageImpl<>(List.of(
                    mock(Reserva.class),
                    mock(Reserva.class),
                    mock(Reserva.class)));
            when(reservaRepository.findAll(any(Pageable.class))).thenReturn(reservasMock);

            Page<Reserva> reservas = reservaService.findAllReservas(Pageable.unpaged());
            verify(reservaRepository, times(1)).findAll(Pageable.unpaged());
            assertThat(reservas).isInstanceOf(Page.class);
            assertThat(reservas.getContent()).isNotEmpty().hasSize(reservasMock.getSize());
        }

        @Test
        void deveBuscarReservas_porId() {
            Reserva reservaMock = gerarReservaMock();
            UUID reservaId = reservaMock.getId();
            when(reservaRepository.findById(any(UUID.class)))
                    .thenReturn(Optional.of(reservaMock));

            Reserva reservaById = reservaService.findReservaById(reservaId);
            verify(reservaRepository, times(1)).findById(reservaId);

            assertThat(reservaById).isNotNull().isEqualTo(reservaMock);
            assertThat(reservaById.getId()).isEqualTo(reservaMock.getId());
            assertThat(reservaById.getCliente()).isEqualTo(reservaMock.getCliente());
            assertThat(reservaById.getQuartos()).isEqualTo(reservaMock.getQuartos())
                    .hasSize(reservaMock.getQuartos().size());
            assertThat(reservaById.getDataCheckIn()).isEqualTo(reservaMock.getDataCheckIn());
            assertThat(reservaById.getDataCheckOut()).isEqualTo(reservaMock.getDataCheckOut());
        }

        @Test
        void deveBuscarReservas_porClienteId() {
            Reserva reservaMock = gerarReservaMock();
            Page<Reserva> pageReservaMock = new PageImpl<>(
                    Collections.singletonList(reservaMock)
            );
            Long clienteId = reservaMock.getCliente().getId();
            when(reservaRepository.findAllByCliente_Id(any(Pageable.class), anyLong()))
                    .thenReturn(pageReservaMock);

            Page<Reserva> reservaByClienteId = reservaService.findReservaByClienteId(Pageable.unpaged(), clienteId);
            verify(reservaRepository, times(1)).findAllByCliente_Id(Pageable.unpaged(), clienteId);

            assertThat(reservaByClienteId).isInstanceOf(Page.class);
            assertThat(reservaByClienteId.getContent()).isNotEmpty().hasSize(pageReservaMock.getSize());
        }
    }

    @Nested
    class InserirReservas {

        @Test
        void deveInserirReserva() {
            Reserva reservaMock = gerarReservaMock();
            Cliente clienteMock = gerarClienteMock();
            List<Quarto> quartoListMock = Collections.singletonList(mock(Quarto.class));
            ReservaDTO reservaDTO = gerarReservaDTOMock();
            when(clienteService.findClienteByEmail(anyString())).thenReturn(clienteMock);
            when(quartoService.findListQuartoById(anyList())).thenReturn(quartoListMock);
            when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

            Reserva reserva = reservaService.createReserva(reservaDTO);
            verify(reservaRepository, times(1)).save(any(Reserva.class));
            verify(clienteService, times(1)).findClienteByEmail(anyString());
            verify(quartoService, times(1)).findListQuartoById(anyList());

            assertThat(reserva).isNotNull();
            assertThat(reserva.getId()).isEqualTo(reservaMock.getId());
            assertThat(reserva.getCliente()).isEqualTo(reservaMock.getCliente());
            assertThat(reserva.getQuartos()).isEqualTo(reservaMock.getQuartos());
            assertThat(reserva.getDataCheckIn()).isEqualTo(reservaMock.getDataCheckIn());
            assertThat(reserva.getDataCheckOut()).isEqualTo(reservaMock.getDataCheckOut());
        }
    }

    @Nested
    class AtualizarReservas {

        @Test
        void deveAtualizarReserva() {
            Reserva reservaMock = gerarReservaMock();
            ReservaDTO reservaDTO = gerarReservaDTOMock();
            UUID reservaId = reservaMock.getId();
            when(reservaRepository.findById(any(UUID.class))).thenReturn(Optional.of(reservaMock));
            when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

            Reserva reserva = reservaService.updateReserva(reservaId, reservaDTO);
            verify(reservaRepository, times(1)).findById(any(UUID.class));
            verify(reservaRepository, times(1)).save(any(Reserva.class));

            assertThat(reserva).isNotNull().isEqualTo(reservaMock);
        }
    }

    @Nested
    class DeletarReservas {

        @Test
        void deveDeletarCliente() {
            Reserva reservaMock = gerarReservaMock();
            UUID reservaId = reservaMock.getId();
            when(reservaRepository.findById(any(UUID.class))).thenReturn(Optional.of(reservaMock));
            doNothing().when(reservaRepository).delete(any(Reserva.class));

            reservaService.deleteReservaById(reservaId);
            verify(reservaRepository, times(1)).findById(reservaId);
            verify(reservaRepository, times(1)).delete(any(Reserva.class));
        }
    }

    @Nested
    class Exceptions {

        @Test
        void deveLancarExcecao_buscarReservaPorId_reservaNaoEncontrada() {
            Reserva reservaMock = gerarReservaMock();
            UUID reservaId = reservaMock.getId();
            when(reservaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

            assertThatThrownBy(() -> reservaService.findReservaById(reservaId))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("reserva_id [%s] não encontrada", reservaId));
            verify(reservaRepository, times(1)).findById(any(UUID.class));
        }

        @Test
        void deveLancarExcecao_deletarReserva_naoEncontrada() {
            UUID reservaId = UUID.randomUUID();
            when(reservaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

            assertThatThrownBy(() -> reservaService.deleteReservaById(reservaId))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("reserva_id [%s] não encontrada", reservaId));
            verify(reservaRepository, times(1)).findById(any(UUID.class));
            verify(reservaRepository, never()).delete(any(Reserva.class));
        }
    }
}
