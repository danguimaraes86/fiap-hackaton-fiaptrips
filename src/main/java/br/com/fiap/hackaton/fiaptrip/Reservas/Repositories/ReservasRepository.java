package br.com.fiap.hackaton.fiaptrip.Reservas.Repositories;

import br.com.fiap.hackaton.fiaptrip.Reservas.Models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Long> {

}
