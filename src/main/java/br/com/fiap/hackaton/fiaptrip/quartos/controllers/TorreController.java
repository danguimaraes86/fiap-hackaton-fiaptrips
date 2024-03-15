package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.services.TorreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/torres")
public class TorreController {
    private final TorreService torreService;
    public TorreController(TorreService torreService) {
        this.torreService = torreService;
    }

    // <>--------------- Metodos ---------------<>
    @GetMapping
    public ResponseEntity<List<Torre>> getAllTorres() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torre> getTorreById(@PathVariable Long id) {
        var torreFound = torreService.findByID(id);

        return ResponseEntity.ok(torreFound);
    }

    @PostMapping
    public ResponseEntity<Torre> createTorre(@RequestBody Torre torre) {
        var torreCreated = torreService.createTorre(torre);

        return ResponseEntity.ok(torreCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torre> updateTorre(@PathVariable Long id, @RequestBody Torre torre) {
        var torreUpdated = torreService.updateTorre(id, torre);

        return ResponseEntity.ok(torreUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTorre(@PathVariable Long id) {
        torreService.deleteByID(id);

        return ResponseEntity.accepted().body("Torre deletada com sucesso!");
    }
}
