package br.com.fiap.hackaton.fiaptrip.Reservas.Models;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Localidade;
import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import br.com.fiap.hackaton.fiaptrip.adicionais.model.Itens;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
    private LocalDateTime dataCheckIn;
    private LocalDateTime dataCheckOut;
    @ManyToOne
    private Quarto quarto;
    @ManyToOne
    private Localidade localidade;
    private List<Itens> itensAdicionais;
    private Long precoFinal;
}
