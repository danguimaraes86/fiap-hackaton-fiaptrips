package br.com.fiap.hackaton.fiaptrip.adicionais.models;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.dto.AdicionalDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.models.enumerator.TipoAdicional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class AdicionalTest {


    @Test
    void constructorTest(){
        Adicional add = new Adicional();
        Adicional adicional = new Adicional("Novo adicional", 50.0, TipoAdicional.PRODUTO);
        adicional.getTipoAdicional();
        adicional.getValor();
        adicional.getId();
        AdicionalDTO dto = new AdicionalDTO(1l, "Novo adicional alterado", 20.0, "produto");
        adicional.update(dto);
        Assertions.assertEquals(adicional.getDescricao(), dto.descricao());
        AdicionalDTO adicionalDTO = adicional.toAdicionalDTO();
        Assertions.assertEquals(adicionalDTO.getClass(), AdicionalDTO.class);
    }

}
