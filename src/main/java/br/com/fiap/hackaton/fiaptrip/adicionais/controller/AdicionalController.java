package br.com.fiap.hackaton.fiaptrip.adicionais.controller;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.service.AdicionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adicionais")
public class AdicionalController {

    private final AdicionalService adicionalService;

    @GetMapping
    public ResponseEntity<Page<AdicionalDTO>> findAllAdicionais(Pageable pageable) {
        Page<Adicional> adicionais = adicionalService.findAllAdicionais(pageable);
        return ResponseEntity.ok(adicionais.map(Adicional::toAdicionalDTO));
    }

    @GetMapping("/busca")
    public ResponseEntity<AdicionalDTO> findAdicionalByDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(adicionalService.findAdicionalByDescricao(descricao).toAdicionalDTO());
    }

    @PostMapping("/novo")
    public ResponseEntity<AdicionalDTO> createNovoAdicional(@RequestBody @Valid AdicionalDTO adicionalDTO) {
        return ResponseEntity.ok(adicionalService.createNovoAdicional(adicionalDTO).toAdicionalDTO());
    }

    @PutMapping("/{adicionalId}")
    public ResponseEntity<AdicionalDTO> updateAdicionalById(
            @PathVariable Long adicionalId, @RequestBody @Valid AdicionalDTO adicionalDTO) {
        return ResponseEntity.ok(adicionalService.updateAdicional(adicionalId, adicionalDTO).toAdicionalDTO());
    }

    @DeleteMapping("/{adicionalId}")
    public ResponseEntity<Void> deleteAdicionalById(@PathVariable Long adicionalId) {
        adicionalService.deleteAdicionalById(adicionalId);
        return ResponseEntity.accepted().build();
    }
}
