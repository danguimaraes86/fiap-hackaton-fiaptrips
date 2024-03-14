package br.com.fiap.hackaton.fiaptrip.quartos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;

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
    private Set<Torre> torres;

    public Localidade(Long id, String nome, String estado, String cidade, String bairro, String rua, String numero, String complemento, String cep, List<String> telefones, String latitude, String longitude, Set<Torre> torres) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.telefones = telefones;
        this.latitude = latitude;
        this.longitude = longitude;
        this.torres = torres;
    }

    public Localidade() {

    }
}
