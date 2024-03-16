package br.com.fiap.hackaton.fiaptrip.quartos.models;

import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.TorreDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Torre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nome;
    @NotNull
    @ManyToOne
    private Localidade localidade;

    public Torre(String nome, Localidade localidade) {
        this.nome = nome;
        this.localidade = localidade;
    }


    public TorreDTO toTorreDTO(){
        return new TorreDTO(this.nome, this.localidade.getId());
    }
}
