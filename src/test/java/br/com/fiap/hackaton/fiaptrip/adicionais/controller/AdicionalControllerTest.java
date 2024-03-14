package br.com.fiap.hackaton.fiaptrip.adicionais.controller;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.service.AdicionalService;
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
import java.util.Random;

import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.gerarAdicional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdicionalControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private AdicionalService service;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        AdicionalController controller = new AdicionalController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    @Nested
    class BuscarAdicionais{

        @Test
        void deveRetornarListaDeAdicionais() throws Exception {
            Page<Adicional> adiconalMock = new PageImpl<>(List.of(
                    mock(Adicional.class),
                    mock(Adicional.class),
                    mock(Adicional.class)));
            when(service.findAllAdicionais(any(Pageable.class)))
                    .thenReturn(adiconalMock);

            mockMvc.perform(get("/adicionais"))
                    .andExpect(status().isOk());
            verify(service, times(1))
                    .findAllAdicionais(any(Pageable.class));
        }

        @Test
        void deveBuscarAdicionalPorDescricao() throws Exception {
            Adicional adicionalMock = gerarAdicional();
            String descricao = adicionalMock.getDescricao();
            AdicionalDTO adicionalDTO = adicionalMock.toAdicionalDTO();
            when(service.findAdicionalByDescricao(anyString()))
                    .thenReturn(adicionalMock);

            mockMvc.perform(get("/adicionais/busca")
                            .param("descricao", descricao))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(adicionalDTO)));
            verify(service, times(1))
                    .findAdicionalByDescricao(anyString());
        }
    }

    @Nested
    class InserirAdicional {

        @Test
        void deveInserirAdicional() throws Exception {
            Adicional adicionalMock = gerarAdicional();
            AdicionalDTO adicionalDTO = adicionalMock.toAdicionalDTO();
            when(service.createNovoAdicional(any(AdicionalDTO.class)))
                    .thenReturn(adicionalMock);

            mockMvc.perform(post("/adicionais/novo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(adicionalDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(adicionalDTO)));
            verify(service, times(1))
                    .createNovoAdicional(any(AdicionalDTO.class));
        }
    }

    @Nested
    class AlterarAdicional {

        @Test
        void deveAlterarAdicional() throws Exception {
            Adicional adicionalMock = gerarAdicional();
            AdicionalDTO adicionalDTO = adicionalMock.toAdicionalDTO();
            Long adicionalId = adicionalMock.getId();
            when(service.updateAdicional(anyLong(), any(AdicionalDTO.class)))
                    .thenReturn(adicionalMock);

            mockMvc.perform(put("/adicionais/{adicionalId}", adicionalId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(adicionalDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(adicionalDTO)));
            verify(service, times(1))
                    .updateAdicional(anyLong(), any(AdicionalDTO.class));
        }
    }

    @Nested
    class ExcluirAdicional {

        @Test
        void deveExcluirAdicional() throws Exception {
            Long adicionalId = new Random().nextLong();
            doNothing().when(service).deleteAdicionalById(anyLong());

            mockMvc.perform(delete("/adicionais/{adicionalId}", adicionalId))
                    .andExpect(status().isAccepted());
            verify(service, times(1))
                    .deleteAdicionalById(anyLong());
        }
    }
}
