package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.LocalidadeRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
public class LocalidadeService {
    private final LocalidadeRepository localidadeRepository;
    public LocalidadeService(LocalidadeRepository localidadeRepository) {
        this.localidadeRepository = localidadeRepository;
    }


    // <>--------------- Metodos ---------------<>
    public Page<Localidade> findAll(Pageable pageable) {
        return localidadeRepository.findAll(pageable);
    }

    public Localidade findById(Long id) {
        return localidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Localidade não encontrada"));
    }
    public Localidade createLocalidade(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }
    public Localidade updateLocalidade(Long id, Localidade localidade) {
        var localidadeFound = localidadeRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Localidade não encontrada"));

        localidadeFound.setNome(localidade.getNome());
        localidadeFound.setEndereco(localidade.getEndereco());

        return localidadeRepository.save(localidade);
    }
    public void deleteByID(Long id) {
        localidadeRepository.delete(
                localidadeRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException(format("localidade_id [%d] não encontrado", id)))
        );
    }
}
