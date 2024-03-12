package br.com.fiap.hackaton.fiaptrip.clientes.controllers;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.services.ClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ClienteControllerTest {

    //    private static final Faker faker = new Faker(Locale.forLanguageTag("pt_BR"));
    AutoCloseable mock;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

//    private static Cliente getClienteMock() {
//        return new Cliente(
//                new Random().nextLong(),
//                faker.witcher().witcher(),
//                faker.country().countryCode3(),
//                faker.date().birthdayLocalDate(),
//                faker.cpf().valid(),
//                faker.passport().valid(),
//                faker.phoneNumber().phoneNumber(),
//                faker.internet().emailAddress(),
//                faker.address().fullAddress()
//        );
//    }

//    public static String convertToJson(Object object) throws JsonProcessingException {
//        return new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .writeValueAsString(object);
//    }

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
        void deveRetornarListaVazia() throws Exception {
            Page<Cliente> clientesMock = new PageImpl<>(Collections.emptyList());
            when(clienteService.findAllClientes(PageRequest.ofSize(10)))
                    .thenReturn(clientesMock);

            mockMvc.perform(get("/clientes").param("size", "10"))
                    .andExpect(status().isOk());
//                    .andExpect(content().json(convertToJson(clientesMock)));
            verify(clienteService, times(1))
                    .findAllClientes(any(Pageable.class));
        }
    }
}
