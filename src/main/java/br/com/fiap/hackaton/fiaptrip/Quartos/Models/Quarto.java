package br.com.fiap.hackaton.fiaptrip.quartos.models;

import br.com.fiap.hackaton.fiaptrip.quartos.models.dtos.QuartoDTO;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.Amenidades;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;


@Getter
@NoArgsConstructor
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoQuarto;
    private Long quantidadePessoas;
    private String quantidadeCamas;
    @ElementCollection
    private List<Amenidades> amenidades = new ArrayList<>();

    public Quarto(TipoQuarto tipoQuarto, List<Amenidades> amenidadesList) {
        this.tipoQuarto = tipoQuarto.getNome();
        this.quantidadePessoas = tipoQuarto.getQuantidadePessoas();
        this.quantidadeCamas = tipoQuarto.getQuantidadeCamas();
        this.amenidades = amenidadesList;
    }

    public void update(QuartoDTO quartoDTO) {
        if (!isEmpty(quartoDTO.tipoQuarto())) {
            this.tipoQuarto = quartoDTO.tipoQuarto().getNome();
            this.quantidadePessoas = quartoDTO.tipoQuarto().getQuantidadePessoas();
            this.quantidadeCamas = quartoDTO.tipoQuarto().getQuantidadeCamas();
        }
        if (!isEmpty(quartoDTO.amenidades())) {
            this.amenidades = quartoDTO.amenidades();
        }
    }
}
