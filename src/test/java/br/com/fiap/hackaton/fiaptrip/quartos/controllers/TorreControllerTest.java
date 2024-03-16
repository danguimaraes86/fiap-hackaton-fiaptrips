package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.TorreDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.services.TorreService;
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

import java.util.List;

import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getTorreMock;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TorreControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private TorreService torreService;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        TorreController torreController = new TorreController(torreService);
        mockMvc = MockMvcBuilders.standaloneSetup(torreController)
//                .setControllerAdvice(ControllerExceptionHandler.class)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class BuscarTorres {

        @Test
        void deveRetornarPaginasTorres() throws Exception {
            Page<Torre> torresMock = new PageImpl<>(List.of(
                    mock(Torre.class),
                    mock(Torre.class),
                    mock(Torre.class)));
            when(torreService.findAll(any(Pageable.class)))
                    .thenReturn(torresMock);

            mockMvc.perform(get("/torres"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(torresMock)));
            verify(torreService, times(1))
                    .findAll(any(Pageable.class));
        }

        @Test
        void deveBuscarTorreByID() throws Exception {
            Torre torreMock = getTorreMock();
            Long torreID = torreMock.getId();
            when(torreService.findByID(anyLong()))
                    .thenReturn(torreMock);

            mockMvc.perform(get("/torres/{id}", torreID))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(torreMock)));
            verify(torreService, times(1))
                    .findByID(anyLong());
        }
    }

    @Nested
    class InserirTorre {

        @Test
        void deveInserirTorre() throws Exception {
            Torre torreMock = getTorreMock();
            when(torreService.createTorre(any(TorreDTO.class)))
                    .thenReturn(torreMock);

            mockMvc.perform(post("/torres")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(torreMock)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(torreMock)));
            verify(torreService, times(1))
                    .createTorre(any(TorreDTO.class));
        }
    }

    @Nested
    class AlterarTorre {

        @Test
        void deveAlterarTorre() throws Exception {
            Torre torreMock = getTorreMock();
            TorreDTO torreDTO = torreMock.toTorreDTO();
            Long torreID = torreMock.getId();
            when(torreService.updateTorre(anyLong(), any(TorreDTO.class)))
                    .thenReturn(torreMock);

            mockMvc.perform(put("/torres/{id}", torreID)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(torreDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(torreMock)));
            verify(torreService, times(1))
                    .updateTorre(anyLong(), any(TorreDTO.class));
        }
    }

    @Nested
    class ExcluirTorre {

        @Test
        void deveExcluirTorre() throws Exception {
            Long torreID = getTorreMock().getId();
            doNothing().when(torreService).deleteByID(anyLong());

            mockMvc.perform(delete("/torres/{id}", torreID))
                    .andExpect(status().isAccepted());
            verify(torreService, times(1))
                    .deleteByID(anyLong());
        }
    }

}
