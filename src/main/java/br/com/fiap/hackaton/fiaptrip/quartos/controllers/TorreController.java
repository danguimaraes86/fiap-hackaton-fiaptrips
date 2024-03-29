package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.TorreDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.services.TorreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Torre>> getAllTorres(Pageable pageable) {
        var torresFounds = torreService.findAll(pageable);

        return ResponseEntity.ok(torresFounds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torre> getTorreById(@PathVariable Long id) {
        var torreFound = torreService.findByID(id);

        return ResponseEntity.ok(torreFound);
    }

    @PostMapping
    public ResponseEntity<Torre> createTorre(@RequestBody TorreDTO torreDTO) {
        var torreCreated = torreService.createTorre(torreDTO);

        return ResponseEntity.ok(torreCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torre> updateTorre(@PathVariable Long id, @RequestBody TorreDTO torreDTO) {
        var torreUpdated = torreService.updateTorre(id, torreDTO);

        return ResponseEntity.ok(torreUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTorre(@PathVariable Long id) {
        torreService.deleteByID(id);

        return ResponseEntity.accepted().body("Torre deletada com sucesso!");
    }
}
