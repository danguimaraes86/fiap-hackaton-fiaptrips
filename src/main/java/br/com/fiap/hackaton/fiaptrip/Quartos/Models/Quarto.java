package br.com.fiap.hackaton.fiaptrip.Quartos.Models;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Adjs.Camas;
import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Adjs.ItemsDoQuarto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Quarto {

    @Id
    private UUID id;
    @Column(unique = true)
    private String Quarto;

    @NonNull
    @ManyToOne
    private Torre torre;
    private String categoriaDeQuarto;
    @OneToMany
    private List<Camas> camas;
    @ManyToMany
    private List<ItemsDoQuarto> itemsDoQuarto;
    private int quantidadeDePessoas;
    private String descricao;
    private Double precoDaDiaria;

    public Quarto(UUID id, String quarto, Torre torre, String categoriaDeQuarto, List<Camas> camas, List<ItemsDoQuarto> itemsDoQuarto, String descricao) {
        this.id = id;
        Quarto = quarto;
        this.torre = torre;
        this.categoriaDeQuarto = categoriaDeQuarto;
        this.camas = camas;
        this.itemsDoQuarto = itemsDoQuarto;
        this.quantidadeDePessoas = camas.stream().mapToInt(Camas::getLugares).sum();
        this.descricao = descricao;
    }
}
