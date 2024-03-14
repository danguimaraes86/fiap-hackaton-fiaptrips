package br.com.fiap.hackaton.fiaptrip.quartos.repositories;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {

}
