package br.com.fiap.hackaton.fiaptrip.Quartos.Services;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import br.com.fiap.hackaton.fiaptrip.Quartos.Repositories.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;
    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }


    // <>--------------- Metodos ---------------<>
    public List<Quarto> findAll() {
        return quartoRepository.findAll();
    }
    public Quarto findById(UUID id) {
        return quartoRepository.findById(id).orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
    }
    public Quarto createQuarto(Quarto quarto) {
        return quartoRepository.save(quarto);
    }
    public Quarto updateQuarto(UUID id, Quarto quarto) {
        var quartoFound = quartoRepository.findById(id).orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        quartoFound.setQuarto(quarto.getQuarto());
        quartoFound.setCategoriaDeQuarto(quarto.getCategoriaDeQuarto());
        quartoFound.setDescricao(quarto.getDescricao());
        quartoFound.setCamas(quarto.getCamas());
        quartoFound.setItemsDoQuarto(quarto.getItemsDoQuarto());
        quartoFound.setTorre(quarto.getTorre());

        return quartoRepository.save(quarto);
    }
    public void deleteById(UUID id) {
        quartoRepository.deleteById(id);
    }
}
