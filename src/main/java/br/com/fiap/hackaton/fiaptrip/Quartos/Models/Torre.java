package br.com.fiap.hackaton.fiaptrip.Quartos.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Torre {

    @Id
    private Long id;
    @Column(unique = true)
    private String nome;
    @NotNull
    @ManyToOne
    private Localidade localidade;
    @OneToMany
    private List<Quarto> quartos;
}
