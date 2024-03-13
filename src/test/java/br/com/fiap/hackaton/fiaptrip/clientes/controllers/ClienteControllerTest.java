package br.com.fiap.hackaton.fiaptrip.clientes.controllers;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.clientes.services.ClienteService;
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

import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.getClienteDtoMock;
import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.getClienteMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClienteControllerTest {

    AutoCloseable mock;
    private MockMvc mockMvc;
    @Mock
    private ClienteService clienteService;

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    @BeforeEach
    void setupUp() {
        mock = MockitoAnnotations.openMocks(this);
        ClienteController clienteController = new ClienteController(clienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
//                .setControllerAdvice(ControllerExceptionHandler.class)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class BuscarClientes {

        @Test
        void deveRetornarLista() throws Exception {
            Page<Cliente> clientesMock = new PageImpl<>(List.of(
                    mock(Cliente.class),
                    mock(Cliente.class),
                    mock(Cliente.class)));
            when(clienteService.findAllClientes(any(Pageable.class)))
                    .thenReturn(clientesMock);

            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(clientesMock)));
            verify(clienteService, times(1))
                    .findAllClientes(any(Pageable.class));
        }

        @Test
        void deveBuscarClientePorEmail() throws Exception {
            Cliente clienteMock = getClienteMock();
            String email = clienteMock.getEmail();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            when(clienteService.findClienteByEmail(anyString()))
                    .thenReturn(clienteMock);

            mockMvc.perform(get("/clientes/busca")
                            .param("email", email))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(clienteDTO)));
            verify(clienteService, times(1))
                    .findClienteByEmail(anyString());
        }
    }

    @Nested
    class InserirCliente {

        @Test
        void deveInserirCliente() throws Exception {
            Cliente clienteMock = getClienteMock();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            when(clienteService.createCliente(any(ClienteDTO.class)))
                    .thenReturn(clienteMock);

            mockMvc.perform(post("/clientes/novo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(clienteDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(clienteDTO)));
            verify(clienteService, times(1))
                    .createCliente(any(ClienteDTO.class));
        }
    }

    @Nested
    class AlterarCliente {

        @Test
        void deveAlterarCliente() throws Exception {
            Cliente clienteMock = getClienteMock();
            ClienteDTO clienteDTO = clienteMock.convertToDTO();
            Long clienteId = clienteMock.getId();
            when(clienteService.updateCliente(anyLong(), any(ClienteDTO.class)))
                    .thenReturn(clienteMock);

            mockMvc.perform(put("/clientes/{id}", clienteId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(convertToJson(clienteDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(convertToJson(clienteDTO)));
            verify(clienteService, times(1))
                    .updateCliente(anyLong(), any(ClienteDTO.class));
        }
    }

    @Nested
    class ExcluirCliente {

        @Test
        void deveExcluirCliente() throws Exception {
            Long clienteId = new Random().nextLong();
            doNothing().when(clienteService).deleteCliente(anyLong());

            mockMvc.perform(delete("/clientes/{id}", clienteId))
                    .andExpect(status().isAccepted());
            verify(clienteService, times(1))
                    .deleteCliente(anyLong());
        }
    }
}
