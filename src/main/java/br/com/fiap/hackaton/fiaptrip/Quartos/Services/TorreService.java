package br.com.fiap.hackaton.fiaptrip.Quartos.Services;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Torre;
import br.com.fiap.hackaton.fiaptrip.Quartos.Repositories.TorreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorreService {
    private final TorreRepository torreRepository;
    public TorreService(TorreRepository torreRepository) {
        this.torreRepository = torreRepository;
    }


    // <>--------------- Metodos ---------------<>
    public List<Torre> findAll() {
        return torreRepository.findAll();
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
        torreRepository.deleteById(id);
    }
}
