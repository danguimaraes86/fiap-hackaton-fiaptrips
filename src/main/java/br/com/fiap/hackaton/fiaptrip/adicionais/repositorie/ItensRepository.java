package br.com.fiap.hackaton.fiaptrip.adicionais.repositorie;

import br.com.fiap.hackaton.fiaptrip.adicionais.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {
}
