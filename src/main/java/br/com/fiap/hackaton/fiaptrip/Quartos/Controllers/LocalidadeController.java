package br.com.fiap.hackaton.fiaptrip.quartos.controllers;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;

import br.com.fiap.hackaton.fiaptrip.quartos.services.LocalidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localidades")
public class LocalidadeController {
    private final LocalidadeService localidadeService;
    public LocalidadeController(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }


    // <>--------------- Metodos ---------------<>
    @GetMapping("/{id}")
    public ResponseEntity<Localidade> getLocalidadeById(@PathVariable Long id) {
        var localidadeFound = localidadeService.findById(id);

        return ResponseEntity.ok(localidadeFound);
    }

    @PostMapping
    public ResponseEntity<Localidade> createLocalidade(@RequestBody Localidade localidade) {
        var localidadeCreated = localidadeService.createLocalidade(localidade);

        return ResponseEntity.ok(localidadeCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localidade> updateLocalidade(@PathVariable Long id, @RequestBody Localidade localidade) {
        var localidadeUpdated = localidadeService.updateLocalidade(id, localidade);

        return ResponseEntity.ok(localidadeUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocalidade(@PathVariable Long id) {
        localidadeService.deleteByID(id);

        return ResponseEntity.ok("Localidade deletada com sucesso!");
    }
}
