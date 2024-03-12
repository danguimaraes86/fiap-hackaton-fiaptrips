package br.com.fiap.hackaton.fiaptrip.adicionais.repositorie;

import br.com.fiap.hackaton.fiaptrip.adicionais.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos, Long> {
}
