package br.com.fiap.hackaton.fiaptrip.adicionais.service;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.repositories.AdicionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AdicionalService {

    private final AdicionalRepository adicionalRepository;

    public Page<Adicional> findAllAdicionais(Pageable pageable) {
        return adicionalRepository.findAll(pageable);
    }

    public Adicional findAdicionalByDescricao(String descricao) {
        return adicionalRepository.findAdicionalByDescricaoContainingIgnoreCase(descricao).orElseThrow(
                () -> new NoSuchElementException(format("adicional [%s] não encontrado", descricao))
        );
    }

    public Adicional createNovoAdicional(AdicionalDTO adicionalDTO) {
        TipoAdicional tipoAdicional = TipoAdicional.valueOf(adicionalDTO.tipoAdicional().toUpperCase());
        return adicionalRepository.save(
                new Adicional(adicionalDTO.descricao(), adicionalDTO.valor(), tipoAdicional));
    }

    public void deleteAdicionalById(Long adicionalId) {
        adicionalRepository.delete(
                adicionalRepository.findById(adicionalId).orElseThrow(
                        () -> new NoSuchElementException(format("adicional [%d] não encontrado", adicionalId)))
        );
    }

    public Adicional updateAdicional(Long adicionalId, AdicionalDTO adicionalDTO) {
        Adicional adicional = adicionalRepository.findById(adicionalId).orElseThrow(
                () -> new NoSuchElementException(format("adicional [%d] não encontrado", adicionalId))
        );
        adicional.update(adicionalDTO);
        return adicionalRepository.save(adicional);
    }
}
