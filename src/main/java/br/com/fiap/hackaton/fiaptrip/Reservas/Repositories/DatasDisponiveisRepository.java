package br.com.fiap.hackaton.fiaptrip.Reservas.Repositories;

import br.com.fiap.hackaton.fiaptrip.Reservas.Models.DatasDisponiveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasDisponiveisRepository extends JpaRepository<DatasDisponiveis, Long> {

}
