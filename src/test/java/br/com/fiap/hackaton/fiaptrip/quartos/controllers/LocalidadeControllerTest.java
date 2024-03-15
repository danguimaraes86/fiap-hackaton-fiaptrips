package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.services.LocalidadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static br.com.fiap.hackaton.fiaptrip.quartos.adjs.MetodosAuxiliaresTestes.getLocalidadeMock;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LocalidadeControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private LocalidadeService localidadeService;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        LocalidadeController localidadeController = new LocalidadeController(localidadeService);
        mockMvc = MockMvcBuilders.standaloneSetup(localidadeController)
//                .setControllerAdvice(ControllerExceptionHandler.class)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class BuscarLocalidades {

        @Test
        public void deveRetornarLocalidade() throws Exception {
            Localidade localidadeMock = getLocalidadeMock();
            when(localidadeService.findById(anyLong()))
                    .thenReturn(localidadeMock);
            when(localidadeService.findById(anyLong()))
                    .thenReturn(localidadeMock);

            mockMvc.perform(get("/localidades/{id}", localidadeMock.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(localidadeMock)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(localidadeMock)));
            verify(localidadeService, times(1))
                    .findById(any(Long.class));
        }

    }

    @Nested
    class InserirLocalidade {

        @Test
        void deveInserirLocalidade() throws Exception {
            Localidade localidadeMock = getLocalidadeMock();
            when(localidadeService.createLocalidade(any(Localidade.class)))
                    .thenReturn(localidadeMock);

            mockMvc.perform(post("/localidades")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(localidadeMock)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(localidadeMock)));
            verify(localidadeService, times(1))
                    .createLocalidade(any(Localidade.class));
        }
    }

    @Nested
    class AlterarCliente {

        @Test
        void deveAlterarLocalidade() throws Exception {
            Localidade localidadeMock = getLocalidadeMock();
            Long localidadeId = localidadeMock.getId();
            when(localidadeService.updateLocalidade(anyLong(), any(Localidade.class)))
                    .thenReturn(localidadeMock);

            mockMvc.perform(put("/localidades/{id}", localidadeId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(localidadeMock)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(localidadeMock)));
            verify(localidadeService, times(1))
                    .updateLocalidade(anyLong(), any(Localidade.class));
        }
    }

    @Nested
    class ExcluirLocalidade {

        @Test
        void deveExcluirCliente() throws Exception {
            Long localidadeId = new Random().nextLong();
            doNothing().when(localidadeService).deleteByID(anyLong());

            mockMvc.perform(delete("/localidades/{id}", localidadeId))
                    .andExpect(status().isOk());
            verify(localidadeService, times(1))
                    .deleteByID(anyLong());
        }
    }


}
