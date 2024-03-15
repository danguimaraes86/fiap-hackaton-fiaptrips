package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.QuartoDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import br.com.fiap.hackaton.fiaptrip.quartos.services.QuartoService;
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


import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getQuartoMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuartoControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private QuartoService quartoService;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        QuartoController quartoController = new QuartoController(quartoService);
        mockMvc = MockMvcBuilders.standaloneSetup(quartoController)
//                .setControllerAdvice(ControllerExceptionHandler.class)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class BuscarQuartos {

        @Test
        void deveRetornarPaginasQuartos() throws Exception {
            Page<Quarto> quartosMock = new PageImpl<>(List.of(
                    mock(Quarto.class),
                    mock(Quarto.class),
                    mock(Quarto.class)));
            when(quartoService.findAllQuartos(any(Pageable.class)))
                    .thenReturn(quartosMock);

            mockMvc.perform(get("/quartos"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(quartosMock)));
            verify(quartoService, times(1))
                    .findAllQuartos(any(Pageable.class));
        }
        @Test
        void deveBuscarQuartosByTipo() throws Exception {
            Quarto quartoMock = getQuartoMock();
            QuartoDTO quartoDTO = new QuartoDTO(TipoQuarto.LUXO, new ArrayList<>(List.of(Amenidades.TV)));
            when(quartoService.findQuartoByTipo(anyString()))
                    .thenReturn(quartoMock);

            mockMvc.perform(get("/quartos/busca")
                            .param("tipoQuarto",quartoDTO.tipoQuarto().getNome().toUpperCase()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(quartoMock)));
            verify(quartoService, times(1))
                    .findQuartoByTipo(anyString());
        }

        @Test
        void deveBuscarQuartoPorID() throws Exception {
            Quarto quartoMock = getQuartoMock();
            Long quartoId = 1L;
            when(quartoService.findQuartoById(anyLong()))
                    .thenReturn(quartoMock);

            mockMvc.perform(get("/quartos/{id}", quartoId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(quartoMock)));
            verify(quartoService, times(1))
                    .findQuartoById(anyLong());
        }
    }

    @Nested
    class InserirQuarto {

        @Test
        void deveInserirQuarto() throws Exception {
            Quarto quartoMock = getQuartoMock();
            QuartoDTO quartoDTO = new QuartoDTO(TipoQuarto.LUXO, new ArrayList<>(List.of(Amenidades.TV)));
            when(quartoService.createNovoQuarto(any(QuartoDTO.class)))
                    .thenReturn(quartoMock);

            mockMvc.perform(post("/quartos/novo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(quartoDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(quartoMock)));
            verify(quartoService, times(1))
                    .createNovoQuarto(any(QuartoDTO.class));
        }
    }

    @Nested
    class AlterarQuarto {

        @Test
        void deveAlterarQuarto() throws Exception {
            Quarto quartoMock = getQuartoMock();
            Long quartoId = 1L;
            QuartoDTO quartoDTO = new QuartoDTO(TipoQuarto.LUXO, new ArrayList<>(List.of(Amenidades.TV)));
            when(quartoService.updateQuarto(anyLong(), any(QuartoDTO.class)))
                    .thenReturn(quartoMock);

            mockMvc.perform(put("/quartos/{quartoId}", quartoId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(quartoDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(quartoMock)));
            verify(quartoService, times(1))
                    .updateQuarto(anyLong(), any(QuartoDTO.class));
        }
    }

    @Nested
    class ExcluirQuarto {

        @Test
        void deveExcluirQuarto() throws Exception {
            Long quartoId = 1L;
            doNothing().when(quartoService).deleteQuartoById(anyLong());

            mockMvc.perform(delete("/quartos/{id}", quartoId))
                    .andExpect(status().isAccepted());
            verify(quartoService, times(1))
                    .deleteQuartoById(anyLong());
        }
    }

}
