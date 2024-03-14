package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.LocalidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalidadeService {
    private final LocalidadeRepository localidadeRepository;
    public LocalidadeService(LocalidadeRepository localidadeRepository) {
        this.localidadeRepository = localidadeRepository;
    }


    // <>--------------- Metodos ---------------<>

    public Localidade findById(Long id) {
        return localidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Localidade não encontrada"));
    }
    public Localidade createLocalidade(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }
    public Localidade updateLocalidade(Long id, Localidade localidade) {
        var localidadeFound = localidadeRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Localidade não encontrada"));

        localidadeFound.setNome(localidade.getNome());

        localidadeFound.setTorres(localidade.getTorres());

        return localidadeRepository.save(localidade);
    }
    public void deleteByID(Long id) {
        localidadeRepository.deleteById(id);
    }
}
