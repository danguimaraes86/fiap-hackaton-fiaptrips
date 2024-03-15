package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.TorreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
public class TorreService {
    private final TorreRepository torreRepository;
    public TorreService(TorreRepository torreRepository) {
        this.torreRepository = torreRepository;
    }


    // <>--------------- Metodos ---------------<>
    public Page<Torre> findAll(Pageable pageable){
        return torreRepository.findAll(pageable);
    }
    public Torre findByID(Long id) {
        return torreRepository.findById(id).orElseThrow(() -> new RuntimeException("Torre não encontrada"));
    }
    public Torre createTorre(Torre torre) {
        return torreRepository.save(torre);
    }
    public Torre updateTorre(Long id, Torre torre) {
        var torreFound = torreRepository.findById(id).orElseThrow(() -> new RuntimeException("Torre não encontrada"));

        torreFound.setNome(torre.getNome());
        torreFound.setLocalidade(torre.getLocalidade());
        torreFound.setQuartos(torre.getQuartos());

        return torreRepository.save(torre);
    }
    public void deleteByID(Long id) {
        torreRepository.delete(
                torreRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException(format("torre_id [%d] não encontrado", id))));
    }
}
