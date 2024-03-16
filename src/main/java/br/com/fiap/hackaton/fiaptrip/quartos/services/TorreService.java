package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.TorreDTO;
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
    private final LocalidadeService localidadeService;
    public TorreService(TorreRepository torreRepository, LocalidadeService localidadeService) {
        this.torreRepository = torreRepository;
        this.localidadeService = localidadeService;
    }


    // <>--------------- Metodos ---------------<>
    public Page<Torre> findAll(Pageable pageable){
        return torreRepository.findAll(pageable);
    }
    public Torre findByID(Long id) {
        return torreRepository.findById(id).orElseThrow(() -> new RuntimeException("Torre não encontrada"));
    }
    public Torre createTorre(TorreDTO torreDTO) {
        var localidade = localidadeService.findById(torreDTO.localidadeId());

        Torre torre = new Torre(torreDTO.nome(), localidade);

        return torreRepository.save(torre);
    }
    public Torre updateTorre(Long id, TorreDTO torreDTO) {
        var torreFound = torreRepository.findById(id).orElseThrow(() -> new RuntimeException("Torre não encontrada"));

        torreFound.setNome(torreDTO.nome());

        return torreRepository.save(torreFound);
    }
    public void deleteByID(Long id) {
        torreRepository.delete(
                torreRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException(format("torre_id [%d] não encontrado", id))));
    }
}
