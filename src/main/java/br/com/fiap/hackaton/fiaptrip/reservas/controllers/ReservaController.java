package br.com.fiap.hackaton.fiaptrip.reservas.controllers;

import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import br.com.fiap.hackaton.fiaptrip.reservas.models.dtos.ReservaDTO;
import br.com.fiap.hackaton.fiaptrip.reservas.services.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Page<Reserva>> findAllReservas(Pageable pageable) {
        return ResponseEntity.ok(reservaService.findAllReservas(pageable));
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<Reserva> findReservaById(@PathVariable UUID reservaId) {
        return ResponseEntity.ok(reservaService.findReservaById(reservaId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<Reserva>> findReservasByClienteId(
            Pageable pageable, @PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.findReservaByClienteId(pageable, clienteId));
    }

    @PostMapping("/novo")
    public ResponseEntity<Reserva> createNovaReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.createReserva(reservaDTO));
    }

    @PutMapping("/{reservaId}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable UUID reservaId, @RequestBody @Valid ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaId, reservaDTO));
    }

    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> deleteReservaById(@PathVariable UUID reservaId) {
        reservaService.deleteReservaById(reservaId);
        return ResponseEntity.accepted().build();
    }
}
