package br.com.fiap.hackaton.fiaptrip.adicionais.service;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.ItemServicoAdicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.ItemServicoAdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.repositories.ItemServicoAdicionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AdicionalService {

    private final ItemServicoAdicionalRepository repository;

    public Page<ItemServicoAdicional> findAllAdicionais(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ItemServicoAdicional findAdicionalByDescricao(String descricao) {
        return repository.findItemServicoAdicionalByDescricaoContainingIgnoreCase(descricao).orElseThrow(
                () -> new NoSuchElementException(format("adicional [%s] não encontrado", descricao))
        );
    }

    public ItemServicoAdicional createNovoAdicional(ItemServicoAdicionalDTO adicionalDTO) {
        TipoAdicional tipoAdicional = TipoAdicional.valueOf(adicionalDTO.tipoAdicional().toUpperCase());
        return repository.save(
                new ItemServicoAdicional(adicionalDTO.descricao(), adicionalDTO.valor(), tipoAdicional));
    }

    public void deleteAdicionalById(Long adicionalId) {
        repository.delete(
                repository.findById(adicionalId).orElseThrow(
                        () -> new NoSuchElementException(format("adicional [%d] não encontrado", adicionalId)))
        );
    }

    public ItemServicoAdicional updateAdicional(Long adicionalId, ItemServicoAdicionalDTO adicionalDTO) {
        ItemServicoAdicional adicional = repository.findById(adicionalId).orElseThrow(
                () -> new NoSuchElementException(format("adicional [%d] não encontrado", adicionalId))
        );
        adicional.update(adicionalDTO);
        return repository.save(adicional);
    }
}
