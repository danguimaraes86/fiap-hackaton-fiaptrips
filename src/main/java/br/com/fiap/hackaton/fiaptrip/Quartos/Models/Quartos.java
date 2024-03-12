package br.com.fiap.hackaton.fiaptrip.Quartos.Models;


import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Adjs.CategoriaDeQuarto;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Quartos {

    private
    private CategoriaDeQuarto categoriaDeQuarto;
    private List<Cama> camas;

}
