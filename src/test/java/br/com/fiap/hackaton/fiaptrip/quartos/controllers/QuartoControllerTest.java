//package br.com.fiap.hackaton.fiaptrip.quartos.controllers;
//
//import br.com.fiap.hackaton.fiaptrip.quartos.services.QuartoService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class QuartoControllerTest {
//
//    AutoCloseable mock;
//    private MockMvc mockMvc;
//    @Mock
//    private QuartoService quartoService;
//
//    public static String convertToJson(Object object) throws JsonProcessingException {
//        return new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .writeValueAsString(object);
//    }
//
//    @BeforeEach
//    void setupUp() {
//        mock = MockitoAnnotations.openMocks(this);
//        QuartoController quartoController = new QuartoController(quartoService);
//        mockMvc = MockMvcBuilders.standaloneSetup(quartoController)
////                .setControllerAdvice(ControllerExceptionHandler.class)
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .build();
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        mock.close();
//    }
//
//    @Nested
//    class BuscarClientes {
//
//        @Test
//        void deveRetornarLista() throws Exception {
//            Page<Cliente> clientesMock = new PageImpl<>(List.of(
//                    mock(Cliente.class),
//                    mock(Cliente.class),
//                    mock(Cliente.class)));
//            when(clienteService.findAllClientes(any(Pageable.class)))
//                    .thenReturn(clientesMock);
//
//            mockMvc.perform(get("/clientes"))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(clientesMock)));
//            verify(clienteService, times(1))
//                    .findAllClientes(any(Pageable.class));
//        }
//
//        @Test
//        void deveBuscarClientePorEmail() throws Exception {
//            Cliente clienteMock = getClienteMock();
//            String email = clienteMock.getEmail();
//            ClienteDTO clienteDTO = clienteMock.toClienteDTO();
//            when(clienteService.findClienteByEmail(anyString()))
//                    .thenReturn(clienteMock);
//
//            mockMvc.perform(get("/clientes/busca")
//                            .param("email", email))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(clienteDTO)));
//            verify(clienteService, times(1))
//                    .findClienteByEmail(anyString());
//        }
//    }
//
//    @Nested
//    class InserirCliente {
//
//        @Test
//        void deveInserirCliente() throws Exception {
//            Cliente clienteMock = getClienteMock();
//            ClienteDTO clienteDTO = clienteMock.toClienteDTO();
//            when(clienteService.createCliente(any(ClienteDTO.class)))
//                    .thenReturn(clienteMock);
//
//            mockMvc.perform(post("/clientes/novo")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(convertToJson(clienteDTO)))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(clienteDTO)));
//            verify(clienteService, times(1))
//                    .createCliente(any(ClienteDTO.class));
//        }
//    }
//
//    @Nested
//    class AlterarCliente {
//
//        @Test
//        void deveAlterarCliente() throws Exception {
//            Cliente clienteMock = getClienteMock();
//            ClienteDTO clienteDTO = clienteMock.toClienteDTO();
//            Long clienteId = clienteMock.getId();
//            when(clienteService.updateCliente(anyLong(), any(ClienteDTO.class)))
//                    .thenReturn(clienteMock);
//
//            mockMvc.perform(put("/clientes/{id}", clienteId)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(convertToJson(clienteDTO)))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(clienteDTO)));
//            verify(clienteService, times(1))
//                    .updateCliente(anyLong(), any(ClienteDTO.class));
//        }
//    }
//
//    @Nested
//    class ExcluirCliente {
//
//        @Test
//        void deveExcluirCliente() throws Exception {
//            Long clienteId = new Random().nextLong();
//            doNothing().when(clienteService).deleteCliente(anyLong());
//
//            mockMvc.perform(delete("/clientes/{id}", clienteId))
//                    .andExpect(status().isAccepted());
//            verify(clienteService, times(1))
//                    .deleteCliente(anyLong());
//        }
//    }
//}
