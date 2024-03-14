//package br.com.fiap.hackaton.fiaptrip.quartos.services;
//
//import br.com.fiap.hackaton.fiaptrip.quartos.repositories.LocalidadeRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class LocalidadeServiceTest {
//
//    private AutoCloseable mocks;
//    private LocalidadeService localidadeService;
//    @Mock
//    private LocalidadeRepository localidadeRepository;
//
//    @BeforeEach
//    void setup() {
//        mocks = openMocks(this);
//        localidadeService = new LocalidadeService(localidadeRepository);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        mocks.close();
//    }
//
//    @Nested
//    class BuscarClientes {
//
//        @Test
//        void deveBuscarClientes_retornaPageableVazio() {
//            Page<Cliente> clientesMock = new PageImpl<>(Collections.emptyList());
//            when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientesMock);
//
//            Page<Cliente> clientes = clienteService.findAllClientes(Pageable.unpaged());
//            verify(clienteRepository, times(1)).findAll(Pageable.unpaged());
//            assertThat(clientes).isInstanceOf(Page.class);
//            assertThat(clientes.getContent()).isEmpty();
//        }
//
//        @Test
//        void deveBuscarClientes_retornaPageable() {
//            Page<Cliente> clientesMock = new PageImpl<>(List.of(
//                    mock(Cliente.class),
//                    mock(Cliente.class),
//                    mock(Cliente.class)));
//            when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientesMock);
//
//            Page<Cliente> clientes = clienteService.findAllClientes(Pageable.unpaged());
//            verify(clienteRepository, times(1)).findAll(Pageable.unpaged());
//            assertThat(clientes).isInstanceOf(Page.class);
//            assertThat(clientes.getContent()).isNotEmpty().hasSize(clientesMock.getSize());
//        }
//
//        @Test
//        void deveBuscarClientes_porEmail() {
//            Cliente clienteMock = getClienteMock();
//            String mockEmail = clienteMock.getEmail();
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.of(clienteMock));
//
//            Cliente clienteByEmail = clienteService.findClienteByEmail(mockEmail);
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(mockEmail);
//
//            assertThat(clienteByEmail).isNotNull().isEqualTo(clienteMock);
//            assertThat(clienteByEmail.getId()).isEqualTo(clienteMock.getId());
//            assertThat(clienteByEmail.getNomeComleto()).isEqualTo(clienteMock.getNomeComleto());
//            assertThat(clienteByEmail.getPaisOrigem()).isEqualTo(clienteMock.getPaisOrigem());
//            assertThat(clienteByEmail.getDataNascimento()).isEqualTo(clienteMock.getDataNascimento());
//            assertThat(clienteByEmail.getCpf()).isEqualTo(clienteMock.getCpf());
//            assertThat(clienteByEmail.getPassaporte()).isEqualTo(clienteMock.getPassaporte());
//            assertThat(clienteByEmail.getTelefone()).isEqualTo(clienteMock.getTelefone());
//            assertThat(clienteByEmail.getEmail()).isEqualTo(clienteMock.getEmail());
//            assertThat(clienteByEmail.getEndereco()).isEqualTo(clienteMock.getEndereco());
//        }
//    }
//
//    @Nested
//    class InserirClientes {
//
//        @Test
//        void deveInserirCliente() {
//            Cliente clienteMock = getClienteMock();
//            ClienteDTO clienteDTO = getClienteDtoMock();
//            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString())).thenReturn(Optional.empty());
//
//            Cliente cliente = clienteService.createCliente(clienteDTO);
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, times(1)).save(any(Cliente.class));
//
//            assertThat(cliente).isNotNull();
//            assertThat(cliente.getNomeComleto()).isEqualTo(clienteMock.getNomeComleto());
//            assertThat(cliente.getPaisOrigem()).isEqualTo(clienteMock.getPaisOrigem());
//            assertThat(cliente.getDataNascimento()).isEqualTo(clienteMock.getDataNascimento());
//            assertThat(cliente.getCpf()).isEqualTo(clienteMock.getCpf());
//            assertThat(cliente.getPassaporte()).isEqualTo(clienteMock.getPassaporte());
//            assertThat(cliente.getTelefone()).isEqualTo(clienteMock.getTelefone());
//            assertThat(cliente.getEmail()).isEqualTo(clienteMock.getEmail());
//            assertThat(cliente.getEndereco()).isEqualTo(clienteMock.getEndereco());
//        }
//    }
//
//    @Nested
//    class AtualizarClientes {
//
//        @Test
//        void deveAtualizarCliente() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            ClienteDTO clienteDTO = getClienteDtoMock();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));
//            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
//
//            Cliente cliente = clienteService.updateCliente(clienteId, clienteDTO);
//            verify(clienteRepository, times(1)).findById(anyLong());
//            verify(clienteRepository, times(1)).save(any(Cliente.class));
//
//            assertThat(cliente).isNotNull();
//            assertThat(cliente.getNomeComleto()).isEqualTo(clienteMock.getNomeComleto());
//            assertThat(cliente.getPaisOrigem()).isEqualTo(clienteMock.getPaisOrigem());
//            assertThat(cliente.getDataNascimento()).isEqualTo(clienteMock.getDataNascimento());
//            assertThat(cliente.getCpf()).isEqualTo(clienteMock.getCpf());
//            assertThat(cliente.getPassaporte()).isEqualTo(clienteMock.getPassaporte());
//            assertThat(cliente.getEmail()).isEqualTo(clienteMock.getEmail());
//            assertThat(cliente.getEndereco()).isEqualTo(clienteMock.getEndereco());
//        }
//    }
//
//    @Nested
//    class DeletarClientes {
//
//        @Test
//        void deveDeletarCliente() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));
//            doNothing().when(clienteRepository).delete(any(Cliente.class));
//
//            clienteService.deleteCliente(clienteId);
//            verify(clienteRepository, times(1)).findById(clienteId);
//            verify(clienteRepository, times(1)).delete(any(Cliente.class));
//        }
//    }
//
//    @Nested
//    class Exceptions {
//
//        @Test
//        void deveLancarExcecao_buscarClientePorEmail_emailNaoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            String clienteEmail = clienteMock.getEmail();
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.findClienteByEmail(clienteEmail))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_email [%s] não encontrado", clienteEmail));
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//        }
//
//        @Test
//        void deveLancarExcecao_inserirCliente_emailJaCadastrado() {
//            Cliente clienteMock = getClienteMock();
//            ClienteDTO clienteDTO = getClienteDtoMock();
//            String clienteEmail = clienteMock.getEmail();
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.of(clienteMock));
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage(format("cliente_email [%s] já cadastrado", clienteEmail));
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }
//
//        @Test
//        void deveLancarExcecao_inserirCliente_brasileiroSemCpf() {
//            ClienteDTO clienteDTO = new ClienteDTO(
//                    null, "teste", "pt_br",
//                    "2020-01-01", null, null,
//                    "99-9999-8888", "teste@teste.com", "endereco"
//            );
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("cpf não pode estar vazio para clientes brasileiros");
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }
//
//        @Test
//        void deveLancarExcecao_inserirCliente_estrangeiroSemPassaporte() {
//            ClienteDTO clienteDTO = new ClienteDTO(
//                    null, "teste", "en-US",
//                    "2020-01-01", null, null,
//                    "99-9999-8888", "teste@teste.com", "endereco"
//            );
//            when(clienteRepository.findClienteByEmailContainingIgnoreCase(anyString()))
//                    .thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.createCliente(clienteDTO))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("passaporte não pode estar vazio para clientes estrangeiros");
//            verify(clienteRepository, times(1)).findClienteByEmailContainingIgnoreCase(anyString());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }
//
//        @Test
//        void deveLancarExcecao_alterarCliente_naoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            ClienteDTO clienteDTO = clienteMock.toClienteDTO();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.updateCliente(clienteId, clienteDTO))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
//            verify(clienteRepository, times(1)).findById(anyLong());
//            verify(clienteRepository, never()).save(any(Cliente.class));
//        }
//
//        @Test
//        void deveLancarExcecao_deletarCliente_naoEncontrado() {
//            Cliente clienteMock = getClienteMock();
//            Long clienteId = clienteMock.getId();
//            when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//            assertThatThrownBy(() -> clienteService.deleteCliente(clienteId))
//                    .isInstanceOf(NoSuchElementException.class)
//                    .hasMessage(format("cliente_id [%d] não encontrado", clienteId));
//            verify(clienteRepository, times(1)).findById(anyLong());
//            verify(clienteRepository, never()).delete(any(Cliente.class));
//        }
//    }
//}
