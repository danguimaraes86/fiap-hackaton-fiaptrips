package br.com.fiap.hackaton.fiaptrip.adicionais.models;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.ItemServicoAdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.springframework.util.ObjectUtils.isEmpty;

@NoArgsConstructor
@Getter
@Entity
public class ItemServicoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private TipoAdicional tipoAdicional;

    public ItemServicoAdicional(String descricao, Double valor, TipoAdicional tipoAdicional) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipoAdicional = tipoAdicional;
    }

    public void update(ItemServicoAdicionalDTO adicionalDTO) {
        if (!isEmpty(adicionalDTO.descricao())) {
            this.descricao = adicionalDTO.descricao();
        }
        if (!isEmpty(adicionalDTO.valor())) {
            this.valor = adicionalDTO.valor();
        }
        if (!isEmpty(adicionalDTO.tipoAdicional())) {
            this.tipoAdicional = TipoAdicional.valueOf(adicionalDTO.tipoAdicional().toUpperCase());
        }
    }

    public ItemServicoAdicionalDTO toAdicionalDTO() {
        return new ItemServicoAdicionalDTO(this.id, this.descricao, this.valor, this.tipoAdicional.name());
    }
}
