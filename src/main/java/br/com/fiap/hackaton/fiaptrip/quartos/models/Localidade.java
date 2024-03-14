package br.com.fiap.hackaton.fiaptrip.quartos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Localidade {

    @Id
    private Long id;
    @Column(unique = true)
    private String nome;
    private String endereco;
    @OneToMany
    private Set<Torre> torres;
}