package br.com.fiap.hackaton.fiaptrip.quartos.repositories;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Torre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TorreRepository extends JpaRepository<Torre, Long> {

}
