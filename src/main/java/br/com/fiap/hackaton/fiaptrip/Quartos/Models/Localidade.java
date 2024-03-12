package br.com.fiap.hackaton.fiaptrip.Quartos.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Localidade {

    @Id
    private Long id;
    @Column(unique = true)
    private String nome;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private List<String> telefones;
    private String latitude;
    private String longitude;

    @OneToMany
    private List<Torre> torres;
}
