package br.com.fiap.hackaton.fiaptrip.Quartos.Repositories;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {

}
