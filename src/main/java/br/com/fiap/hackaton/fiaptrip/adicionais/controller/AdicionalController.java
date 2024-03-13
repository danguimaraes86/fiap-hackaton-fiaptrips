package br.com.fiap.hackaton.fiaptrip.adicionais.controller;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.ItemServicoAdicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.ItemServicoAdicionalDTO;
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
    public ResponseEntity<Page<ItemServicoAdicionalDTO>> findAllAdicionais(Pageable pageable) {
        Page<ItemServicoAdicional> adicionais = adicionalService.findAllAdicionais(pageable);
        return ResponseEntity.ok(adicionais.map(ItemServicoAdicional::toAdicionalDTO));
    }

    @GetMapping("/busca")
    public ResponseEntity<ItemServicoAdicionalDTO> findAdicionalByDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(adicionalService.findAdicionalByDescricao(descricao).toAdicionalDTO());
    }

    @PostMapping("/novo")
    public ResponseEntity<ItemServicoAdicionalDTO> createNovoAdicional(@RequestBody @Valid ItemServicoAdicionalDTO adicionalDTO) {
        return ResponseEntity.ok(adicionalService.createNovoAdicional(adicionalDTO).toAdicionalDTO());
    }

    @PutMapping("/{adicionalId}")
    public ResponseEntity<ItemServicoAdicionalDTO> updateAdicionalById(
            @PathVariable Long adicionalId, @RequestBody @Valid ItemServicoAdicionalDTO adicionalDTO) {
        return ResponseEntity.ok(adicionalService.updateAdicional(adicionalId, adicionalDTO).toAdicionalDTO());
    }

    @DeleteMapping("/{adicionalId}")
    public ResponseEntity<Void> deleteAdicionalById(@PathVariable Long adicionalId) {
        adicionalService.deleteAdicionalById(adicionalId);
        return ResponseEntity.accepted().build();
    }
}
