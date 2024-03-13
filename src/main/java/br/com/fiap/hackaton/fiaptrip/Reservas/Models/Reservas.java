package br.com.fiap.hackaton.fiaptrip.Reservas.Models;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Localidade;
import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import br.com.fiap.hackaton.fiaptrip.adicionais.model.Itens;
import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "reservas")
public class Reservas {

    @Id
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Quarto quarto;
    @ManyToOne
    private Localidade localidade;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private Long totalDiarias;
    private Long totalHospedes;
    @OneToMany
    private List<Itens> itensAdicionais;
    private Double precoFinal;

    public void calcularPrecoFinal() {
        Double precoFinal = (quarto.getPrecoDaDiaria() * totalDiarias);
        precoFinal += itensAdicionais.stream().mapToDouble(Itens::getValor).sum();

        this.precoFinal = precoFinal;
    }
}
