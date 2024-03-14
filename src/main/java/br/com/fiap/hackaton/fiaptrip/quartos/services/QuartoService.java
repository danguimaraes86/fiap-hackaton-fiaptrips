package br.com.fiap.hackaton.fiaptrip.quartos.services;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.QuartoDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import br.com.fiap.hackaton.fiaptrip.quartos.repositories.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Page<Quarto> findAllQuartos(Pageable pageable) {
        return quartoRepository.findAll(pageable);
    }

    public Quarto findQuartoByTipo(String tipoQuarto) {
        return quartoRepository.findQuartoByTipoQuartoIgnoreCase(tipoQuarto).orElseThrow(
                () -> new NoSuchElementException(format("tipo_quarto [%s] não encontrado", tipoQuarto))
        );
    }

    public Quarto findQuartoById(Long quartoId) {
        return quartoRepository.findById(quartoId).orElseThrow(
                () -> new NoSuchElementException(format("quarto_id [%d] não encontrado", quartoId))
        );
    }

    public List<Quarto> findListQuartoById(List<Long> quartosIds){
        return quartoRepository.findAllById(quartosIds);
    }

    public Quarto createNovoQuarto(QuartoDTO quartoDTO) {
        return quartoRepository.save(
                new Quarto(quartoDTO.tipoQuarto(), quartoDTO.amenidades()));
    }

    public void deleteQuartoById(Long quartoId) {
        quartoRepository.delete(
                quartoRepository.findById(quartoId).orElseThrow(
                        () -> new NoSuchElementException(format("quarto_id [%d] não encontrado", quartoId)))
        );
    }

    public Quarto updateQuarto(Long quartoId, QuartoDTO quartoDTO) {
        Quarto quarto = quartoRepository.findById(quartoId).orElseThrow(
                () -> new NoSuchElementException(format("quarto [%d] não encontrado", quartoId))
        );
        quarto.update(quartoDTO);
        return quartoRepository.save(quarto);
    }

    public List<Amenidades> showAllAmenidades() {
        return Arrays.stream(Amenidades.values()).toList();
    }

    public List<TipoQuarto> showAllTipoQuarto() {
        return Arrays.stream(TipoQuarto.values()).toList();
    }
}
