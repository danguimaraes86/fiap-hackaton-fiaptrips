package br.com.fiap.hackaton.fiaptrip.Quartos.Models.Adjs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CategoriaDeQuarto {

    @Id
    private UUID id;
    private String categoria;
    private List<ItemsDoQuarto> itensDoQaurto;
    private int maxPessoas;
}
