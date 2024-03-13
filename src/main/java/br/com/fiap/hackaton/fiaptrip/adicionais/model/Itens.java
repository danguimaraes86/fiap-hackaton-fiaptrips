package br.com.fiap.hackaton.fiaptrip.adicionais.model;

import br.com.fiap.hackaton.fiaptrip.adicionais.dto.ItensDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.enumerator.ItensEnum;
import br.com.fiap.hackaton.fiaptrip.adicionais.enumerator.ServicosEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private String servicoItem;

    private ItensEnum itensEnum;

    public Itens(Double valor, String servicoItem, ItensEnum itensEnum) {
        this.valor = valor;
        this.servicoItem = servicoItem;
        this.itensEnum = itensEnum;
    }

    public ItensDTO toDTO(){
        return new ItensDTO(this.id, this.valor, this.servicoItem, this.itensEnum);
    }
}
