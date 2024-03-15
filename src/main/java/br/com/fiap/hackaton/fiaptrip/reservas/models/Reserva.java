package br.com.fiap.hackaton.fiaptrip.reservas.models;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
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
    @ElementCollection
    private Map<Adicional, Long> adicionalList;

    public Reserva(Cliente cliente, List<Quarto> quartos, LocalDate dataCheckIn, LocalDate dataCheckOut, Map<Adicional, Long> adicionalList) {
        this.cliente = cliente;
        this.quartos = quartos;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.adicionalList = adicionalList;
    }

    public void update(Cliente cliente, List<Quarto> quartosList, LocalDate dataCheckIn, LocalDate dataCheckOut, Map<Adicional, Long> adicionalList) {
        this.cliente = cliente;
        this.quartos = quartosList;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.adicionalList = adicionalList;
    }
}
