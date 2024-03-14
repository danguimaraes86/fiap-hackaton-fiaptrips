package br.com.fiap.hackaton.fiaptrip.quartos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
