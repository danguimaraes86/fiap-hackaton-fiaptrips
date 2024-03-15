package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.QuartoDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import br.com.fiap.hackaton.fiaptrip.quartos.services.QuartoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/quartos")
@RestController
public class QuartoController {

    private final QuartoService quartoService;

    @GetMapping
    public ResponseEntity<Page<Quarto>> findAllQuartos(Pageable pageable) {
        return ResponseEntity.ok(quartoService.findAllQuartos(pageable));
    }

    @GetMapping("/{quartoId}")
    public ResponseEntity<Quarto> findQuartoById(@PathVariable Long quartoId) {
        return ResponseEntity.ok(quartoService.findQuartoById(quartoId));
    }

    @GetMapping("/busca")
    public ResponseEntity<Quarto> findQuartoByTipo(@RequestParam String tipoQuarto) {
        return ResponseEntity.ok(quartoService.findQuartoByTipo(tipoQuarto));
    }

    @GetMapping("/amenidades")
    public ResponseEntity<List<Amenidades>> showAllAmenidades() {
        return ResponseEntity.ok(quartoService.showAllAmenidades());
    }

    @GetMapping("/tipoquarto")
    public ResponseEntity<List<TipoQuarto>> showAllTipoQuarto() {
        return ResponseEntity.ok(quartoService.showAllTipoQuarto());
    }

    @PostMapping("/novo")
    public ResponseEntity<Quarto> createQuarto(@RequestBody QuartoDTO quartoDTO) {
        return ResponseEntity.ok(quartoService.createNovoQuarto(quartoDTO));
    }

    @PutMapping("/{quartoId}")
    public ResponseEntity<Quarto> updateQuarto(@PathVariable Long quartoId, @RequestBody QuartoDTO quartoDTO) {
        return ResponseEntity.ok(quartoService.updateQuarto(quartoId, quartoDTO));
    }

    @DeleteMapping("/{quartoId}")
    public ResponseEntity<String> deleteQuarto(@PathVariable Long quartoId) {
        quartoService.deleteQuartoById(quartoId);
        return ResponseEntity.accepted().body("Quarto deletada com sucesso!");
    }
}
