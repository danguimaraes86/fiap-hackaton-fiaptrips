package br.com.fiap.hackaton.fiaptrip.reservas.models;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany
    private List<Quarto> quartos;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;

    public Reserva(Cliente cliente, List<Quarto> quartos, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        this.cliente = cliente;
        this.quartos = quartos;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
    }
}
