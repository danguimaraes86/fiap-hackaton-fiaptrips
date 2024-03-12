package br.com.fiap.hackaton.fiaptrip.Quartos.Controllers;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import br.com.fiap.hackaton.fiaptrip.Quartos.Services.QuartoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class QuartoController {
    private final QuartoService quartoService;
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }


    // <>--------------- Metodos ---------------<>
    @GetMapping
    public ResponseEntity<List<Quarto>> getAllQuartos() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quarto> getQuartoById(UUID id) {
        var quartoFound = quartoService.findById(id);

        return ResponseEntity.ok(quartoFound);
    }
    @PostMapping
    public ResponseEntity<Quarto> createQuarto(@RequestBody Quarto quarto) {
        var quartoCreated = quartoService.createQuarto(quarto);

        return ResponseEntity.ok(quartoCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> updateQuarto(@PathVariable UUID id, @RequestBody Quarto quarto) {
        var quartoUpdated = quartoService.updateQuarto(id, quarto);

        return ResponseEntity.ok(quartoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuarto(@PathVariable UUID id) {
        quartoService.deleteById(id);

        return ResponseEntity.ok("Quarto deletado com sucesso!");
    }
}
