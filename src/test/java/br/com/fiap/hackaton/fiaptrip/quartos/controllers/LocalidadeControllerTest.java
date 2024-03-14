//package br.com.fiap.hackaton.fiaptrip.quartos.controllers;
//
//import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
//import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
//import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
//import br.com.fiap.hackaton.fiaptrip.quartos.services.LocalidadeService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Locale;
//import java.util.Random;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class LocalidadeControllerTest {
//
//    AutoCloseable mock;
//    private MockMvc mockMvc;
//    @Mock
//    private LocalidadeService localidadeService;
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
//        LocalidadeController localidadeController = new LocalidadeController(localidadeService);
//        mockMvc = MockMvcBuilders.standaloneSetup(localidadeController)
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
//    class BuscarLocalidades {
//
//        @Test
//        public void deveRetornarLocalidade() throws Exception {
//            Localidade localidadeMock = getLocalidadeMock();
//            when(localidadeService.findById(anyLong()))
//                    .thenReturn(localidadeMock);
//            when(localidadeService.findById(anyLong()))
//                    .thenReturn(localidadeMock);
//
//            mockMvc.perform(get("/localidades"))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(localidadeMock)));
//            verify(localidadeService, times(1))
//                    .findById(any(Long.class));
//        }
//
//    }
//
//    @Nested
//    class InserirLocalidade {
//
//        @Test
//        void deveInserirLocalidade() throws Exception {
//            Localidade localidadeMock = getLocalidadeMock();
//            when(localidadeService.createLocalidade(any(Localidade.class)))
//                    .thenReturn(localidadeMock);
//
//            mockMvc.perform(post("/localidades")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(convertToJson(localidadeMock)))
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(convertToJson(localidadeMock)));
//            verify(localidadeService, times(1))
//                    .createLocalidade(any(Localidade.class));
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
//
//    public static Localidade getLocalidadeMock() {
//        return new Localidade(
//                new Random().nextLong(),
//                "Localidade Teste",
//                "Rua teste abençoado, 25 - São Paulo",
//                new HashSet<>(List.of(
//                        new Torre(),
//                        new Torre()
//                ))
//
//        );
//    }
//}
