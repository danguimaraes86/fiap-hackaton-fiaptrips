package br.com.fiap.hackaton.fiaptrip.Reservas.Models;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Localidade;
import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class DatasDisponiveis {

    @Id
    private Long id;
    private LocalDate data;
    private boolean CheckInDisponibilidade;
    private boolean CheckOutDisponibilidade;
    @ManyToOne
    private Quarto quarto;
    @ManyToOne
    private Localidade localidade;
}
