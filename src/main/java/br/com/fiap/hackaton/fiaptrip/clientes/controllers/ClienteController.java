package br.com.fiap.hackaton.fiaptrip.clientes.controllers;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.models.ClienteDTO;
import br.com.fiap.hackaton.fiaptrip.clientes.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> findAllClientes(Pageable pageable) {
        Page<Cliente> clientes = clienteService.findAllClientes(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/busca")
    public ResponseEntity<ClienteDTO> findClienteByEmail(@RequestParam String email) {
        return ResponseEntity.ok(clienteService.findClienteByEmail(email).toClienteDTO());
    }

    @PostMapping("/novo")
    public ResponseEntity<ClienteDTO> createNovoCliente(@RequestBody @Valid ClienteDTO cliente) {
        return ResponseEntity.ok(clienteService.createCliente(cliente).toClienteDTO());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> updateClienteById(
            @PathVariable Long clienteId, @RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateCliente(clienteId, clienteDTO).toClienteDTO());
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId) {
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.accepted().build();
    }
}
