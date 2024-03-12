package br.com.fiap.hackaton.fiaptrip.clientes.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.repositories.ClienteRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Locale;

import static org.mockito.MockitoAnnotations.openMocks;

public class ClienteServiceTest {

    private static final Faker faker = new Faker(Locale.forLanguageTag("pt_BR"));

    AutoCloseable mocks;
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    private static Cliente getUsuarioMock() {
        return new Cliente();
    }

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        clienteService = new ClienteService(clienteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarClientes {

        @Test
        void deveBuscarClientes_retornaPageableVazio() {
        }

        @Test
        void deveBuscarClientes_retornaPageable() {
        }

        @Test
        void deveBuscarClientes_porEmail() {
        }
    }

    @Nested
    class InserirClientes {

        @Test
        void deveInserirCliente() {
        }
    }

    @Nested
    class AtualizarClientes {

        @Test
        void deveAtualizarCliente() {
        }
    }


}


//
//@Nested
//class BuscarUsuarios {
//
//    @Test
//    void deveBuscarUsuarios_RetornaPageableVazio() {
//        Page<Usuario> usuarioPageFake = new PageImpl<>(Collections.emptyList());
//        when(usuarioRespository.findAll(any(Pageable.class))).thenReturn(usuarioPageFake);
//
//        Page<Usuario> usuarioPage = usuarioService.findUsuarioByEmailOrNome(
//                Pageable.unpaged(), "", "");
//        verify(usuarioRespository, times(1)).findAll(Pageable.unpaged());
//
//        assertThat(usuarioPage).isInstanceOf(Page.class);
//        assertThat(usuarioPage.getContent()).isEmpty();
//    }
//
//    @Test
//    void deveBuscarUsuarios_RetornaPageable() {
//        Page<Usuario> usuarioPageFake = new PageImpl<>(List.of(
//                getUsuarioMock(),
//                getUsuarioMock(),
//                getUsuarioMock()
//        ));
//        when(usuarioRespository.findByEmailContainingIgnoreCaseOrNomeContainingIgnoreCase(
//                any(Pageable.class), anyString(), anyString())).thenReturn(usuarioPageFake);
//
//        Page<Usuario> usuarioPage = usuarioService.findUsuarioByEmailOrNome(
//                Pageable.unpaged(), "teste", "teste");
//        verify(usuarioRespository, times(1))
//                .findByEmailContainingIgnoreCaseOrNomeContainingIgnoreCase(
//                        Pageable.unpaged(), "teste", "teste");
//
//        assertThat(usuarioPage).isInstanceOf(Page.class);
//        assertThat(usuarioPage.getTotalElements()).isEqualTo(usuarioPageFake.getTotalElements());
//        assertThat(usuarioPage.getContent()).isEqualTo(usuarioPageFake.getContent());
//    }
//
//    @Test
//    void deveBuscarUsuarioPorEmail_comSucesso() {
//        Usuario usuarioMock = getUsuarioMock();
//        String email = usuarioMock.getEmail();
//        when(usuarioRespository.findById(anyString())).thenReturn(Optional.of(usuarioMock));
//
//        Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(email);
//        verify(usuarioRespository, times(1)).findById(email);
//
//        assertThat(usuario).isNotEmpty().isEqualTo(Optional.of(usuarioMock));
//        assertThat(usuario.get().getEmail()).isEqualTo(usuarioMock.getEmail());
//        assertThat(usuario.get().getNome()).isEqualTo(usuarioMock.getNome());
//        assertThat(usuario.get().getPassword()).isEqualTo(usuarioMock.getPassword());
//        assertThat(usuario.get().getRole()).isEqualTo(Role.USER);
//    }
//
//    @Test
//    void deveBuscarUsuarioPorEmail_optionalVazio() {
//        String email = faker.internet().emailAddress();
//        when(usuarioRespository.findById(anyString())).thenReturn(Optional.empty());
//
//        Optional<Usuario> usuario = usuarioService.findUsuarioByEmail(email);
//        verify(usuarioRespository, times(1)).findById(email);
//        assertThat(usuario).isEmpty();
//    }
//}
//
//@Nested
//class InserirUsuario {
//
//    @Test
//    void deveInserirUsuario_comSucesso() {
//        Usuario usuarioMock = getUsuarioMock();
//        UsuarioDTO usuarioDTO = usuarioMock.toUsuarioDTO();
//        when(usuarioRespository.save(any(Usuario.class))).thenReturn(usuarioMock);
//
//        Usuario usuario = usuarioService.createUsuario(usuarioDTO);
//        verify(usuarioRespository, times(1)).save(any(Usuario.class));
//
//        assertThat(usuario.getEmail()).isEqualTo(usuarioMock.getEmail());
//        assertThat(usuario.getNome()).isEqualTo(usuarioMock.getNome());
//        assertThat(usuario.getPassword()).isEqualTo(usuarioMock.getPassword());
//        assertThat(usuario.getRole()).isEqualTo(usuarioMock.getRole());
//    }
//}
//
//@Nested
//class Exceptions {
//
//    @Test
//    void deveLancarExcecao_inserirUsuario_usuarioJaCadastrado() {
//        Usuario usuarioMock = getUsuarioMock();
//        UsuarioDTO usuarioDTO = usuarioMock.toUsuarioDTO();
//        when(usuarioRespository.findById(anyString())).thenReturn(Optional.of(usuarioMock));
//
//        assertThatThrownBy(() -> usuarioService.createUsuario(usuarioDTO))
//                .isInstanceOf(UsuarioJaCadastradoException.class)
//                .hasMessage(String.format("usario_email %s já existe", usuarioDTO.email()));
//        verify(usuarioRespository, times(1)).findById(anyString());
//    }
//}