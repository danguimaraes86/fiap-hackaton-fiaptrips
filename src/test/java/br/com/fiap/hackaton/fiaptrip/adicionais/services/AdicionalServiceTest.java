package br.com.fiap.hackaton.fiaptrip.adicionais.services;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.repositories.AdicionalRepository;
import br.com.fiap.hackaton.fiaptrip.adicionais.service.AdicionalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.gerarAdicionalMock;
import static br.com.fiap.hackaton.fiaptrip.utilitarios.Generator.gerarAdicionalDTOMock;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AdicionalServiceTest {

    private AutoCloseable mocks;
    private AdicionalService service;
    @Mock
    private AdicionalRepository repository;

    @BeforeEach
    void setup() {
        mocks = openMocks(this);
        service = new AdicionalService(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Nested
    class BuscarAdiconais {

        @Test
        void deveBuscarAdiconais_retornaPageableVazio() {
            Page<Adicional> adiconalMock = new PageImpl<>(Collections.emptyList());
            when(repository.findAll(any(Pageable.class))).thenReturn(adiconalMock);

            Page<Adicional> adicionais = service.findAllAdicionais(Pageable.unpaged());
            verify(repository, times(1)).findAll(Pageable.unpaged());
            assertThat(adicionais).isInstanceOf(Page.class);
            assertThat(adicionais.getContent()).isEmpty();
        }

        @Test
        void deveBuscarAdiconais_retornaPageable() {
            Page<Adicional> adicionaisMock = new PageImpl<>(List.of(
                    mock(Adicional.class),
                    mock(Adicional.class),
                    mock(Adicional.class)));
            when(repository.findAll(any(Pageable.class))).thenReturn(adicionaisMock);

            Page<Adicional> adicionais = service.findAllAdicionais(Pageable.unpaged());
            verify(repository, times(1)).findAll(Pageable.unpaged());
            assertThat(adicionais).isInstanceOf(Page.class);
            assertThat(adicionais.getContent()).isNotEmpty().hasSize(adicionaisMock.getSize());
        }

        @Test
        void deveBuscarAdiconal_porDescricao() {
            Adicional adicionalMock = gerarAdicionalMock();
            String descricao = adicionalMock.getDescricao();
            when(repository.findAdicionalByDescricaoContainingIgnoreCase(anyString()))
                    .thenReturn(Optional.of(adicionalMock));

            Adicional adicionalByDescricao = service.findAdicionalByDescricao(descricao);
            verify(repository, times(1)).findAdicionalByDescricaoContainingIgnoreCase(descricao);

            assertThat(adicionalByDescricao.getDescricao()).isNotNull().isEqualTo(descricao);
            assertThat(adicionalByDescricao.getId()).isEqualTo(adicionalMock.getId());
        }
    }

    @Nested
    class InserirAdiconais {

        @Test
        void deveInserirUmNovoAdicional() {
            Adicional adicionalMock = gerarAdicionalMock();
            AdicionalDTO adicionalDTO = gerarAdicionalDTOMock();
            when(repository.save(any(Adicional.class))).thenReturn(adicionalMock);
            when(repository.findAdicionalByDescricaoContainingIgnoreCase(anyString())).thenReturn(Optional.empty());

            Adicional adicional = service.createNovoAdicional(adicionalDTO);
            verify(repository, times(1)).save(any(Adicional.class));

            assertThat(adicional).isNotNull();
            assertThat(adicional.getDescricao()).isEqualTo(adicionalMock.getDescricao());
            assertThat(adicional.getValor()).isEqualTo(adicionalMock.getValor());
        }
    }

    @Nested
    class AtualizarAdiconais {

        @Test
        void deveAlterarAdicional() {
            Adicional adicionalMock = gerarAdicionalMock();
            Long adicionalId = adicionalMock.getId();
            AdicionalDTO adicionalDTO = gerarAdicionalDTOMock();
            when(repository.findById(anyLong())).thenReturn(Optional.of(adicionalMock));
            when(repository.save(any(Adicional.class))).thenReturn(adicionalMock);

            Adicional adicional = service.updateAdicional(adicionalId, adicionalDTO);
            verify(repository, times(1)).findById(anyLong());
            verify(repository, times(1)).save(any(Adicional.class));

            assertThat(adicional).isNotNull();
            assertThat(adicional.getDescricao()).isEqualTo(adicionalMock.getDescricao());
            assertThat(adicional.getValor()).isEqualTo(adicionalMock.getValor());
        }
    }

    @Nested
    class DeletarAdiconais {

        @Test
        void deveExcluirAdicional() {
            Adicional adicionalMock = gerarAdicionalMock();
            Long adicionalId = adicionalMock.getId();
            when(repository.findById(anyLong())).thenReturn(Optional.of(adicionalMock));
            doNothing().when(repository).delete(any(Adicional.class));

            service.deleteAdicionalById(adicionalId);
            verify(repository, times(1)).findById(adicionalId);
            verify(repository, times(1)).delete(any(Adicional.class));
        }
    }

    @Nested
    class Exceptions {

        @Test
        void deveLancarExcecao_buscarAdicinalPorDescricao_NaoEncontrada() {
            Adicional adicionalMock = gerarAdicionalMock();
            String descricao = adicionalMock.getDescricao();
            when(repository.findAdicionalByDescricaoContainingIgnoreCase(anyString())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> service.findAdicionalByDescricao(descricao))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("adicional [%s] não encontrado", descricao));
            verify(repository, times(1)).findAdicionalByDescricaoContainingIgnoreCase(anyString());
        }

        @Test
        void deveLancarExcecao_alterarAdicional_naoEncontrado() {
            Adicional adicionalMock = gerarAdicionalMock();
            Long adicionalId = adicionalMock.getId();
            AdicionalDTO adicionalDTO = adicionalMock.toAdicionalDTO();
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> service.updateAdicional(adicionalId, adicionalDTO))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("adicional [%d] não encontrado", adicionalId));
            verify(repository, times(1)).findById(anyLong());
            verify(repository, never()).save(any(Adicional.class));
        }

        @Test
        void deveLancarExcecao_deletarAdicional_naoEncontrado() {
            Adicional adicionalMock = gerarAdicionalMock();
            Long adicionalId = adicionalMock.getId();
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> service.deleteAdicionalById(adicionalId))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage(format("adicional [%d] não encontrado", adicionalId));
            verify(repository, times(1)).findById(anyLong());
            verify(repository, never()).delete(any(Adicional.class));
        }
    }
}
