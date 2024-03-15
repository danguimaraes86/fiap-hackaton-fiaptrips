package br.com.fiap.hackaton.fiaptrip.reservas.controllers;

import br.com.fiap.hackaton.fiaptrip.reservas.controllers.ReservaController;
import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import br.com.fiap.hackaton.fiaptrip.reservas.models.dtos.ReservaDTO;
import br.com.fiap.hackaton.fiaptrip.reservas.services.ReservaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.gerarReservaDTOMock;
import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.gerarReservaMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReservaControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private ReservaService reservaService;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        ReservaController reservaController = new ReservaController(reservaService);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController)
//                .setControllerAdvice(ControllerExceptionHandler.class)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class BuscarReservas {

        @Test
        void deveRetornarLista() throws Exception {
            Page<Reserva> reservasMock = new PageImpl<>(List.of(
                    mock(Reserva.class),
                    mock(Reserva.class),
                    mock(Reserva.class)));
            when(reservaService.findAllReservas(any(Pageable.class)))
                    .thenReturn(reservasMock);

            mockMvc.perform(get("/reservas"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(reservasMock)));
            verify(reservaService, times(1))
                    .findAllReservas(any(Pageable.class));
        }

        @Test
        void deveBuscarReservaPorId() throws Exception {
            Reserva reservaMock = gerarReservaMock();
            UUID reservaId = reservaMock.getId();
            when(reservaService.findReservaById(reservaId))
                    .thenReturn(reservaMock);

            mockMvc.perform(get("/reservas/{id}", reservaId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(reservaMock)));
            verify(reservaService, times(1))
                    .findReservaById(reservaId);
        }

        @Test
        void deveBuscarReservaPorClienteId() throws Exception {
            Reserva reservaMock = gerarReservaMock();
            Page<Reserva> pageReservaMock = new PageImpl<>(Collections.singletonList(reservaMock));
            Long clienteId = reservaMock.getCliente().getId();
            when(reservaService.findReservaByClienteId(any(Pageable.class), anyLong()))
                    .thenReturn(pageReservaMock);

            mockMvc.perform(get("/reservas/cliente/{id}", clienteId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(pageReservaMock)));
            verify(reservaService, times(1))
                    .findReservaByClienteId(any(Pageable.class), anyLong());
        }
    }

    @Nested
    class InserirReserva {

        @Test
        void deveInserirReserva() throws Exception {
            Reserva reservaMock = gerarReservaMock();
            ReservaDTO reservaDTO = gerarReservaDTOMock();
            when(reservaService.createReserva(any(ReservaDTO.class)))
                    .thenReturn(reservaMock);

            mockMvc.perform(post("/reservas/novo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(reservaDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(reservaMock)));
            verify(reservaService, times(1))
                    .createReserva(any(ReservaDTO.class));
        }
    }

    @Nested
    class AlterarReserva {

        @Test
        void deveAlterarReserva() throws Exception {
            Reserva reservaMock = gerarReservaMock();
            ReservaDTO reservaDTO = gerarReservaDTOMock();
            UUID reservaId = reservaMock.getId();
            when(reservaService.updateReserva(any(UUID.class), any(ReservaDTO.class)))
                    .thenReturn(reservaMock);

            mockMvc.perform(put("/reservas/{reservaId}", reservaId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(reservaDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(reservaMock)));
            verify(reservaService, times(1)).updateReserva(reservaId, reservaDTO);
        }
    }

    @Nested
    class ExcluirReserva {

        @Test
        void deveExcluirReserva() throws Exception {
            UUID reservaId = UUID.randomUUID();
            doNothing().when(reservaService).deleteReservaById(reservaId);

            mockMvc.perform(delete("/reservas/{id}", reservaId))
                    .andExpect(status().isAccepted());
            verify(reservaService, times(1))
                    .deleteReservaById(reservaId);
        }
    }
}
