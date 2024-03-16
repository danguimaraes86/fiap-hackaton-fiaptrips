package br.com.fiap.hackaton.fiaptrip.quartos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Localidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String endereco;

    public Localidade(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
}
