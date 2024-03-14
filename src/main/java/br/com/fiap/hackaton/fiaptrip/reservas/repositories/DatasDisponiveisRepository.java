package br.com.fiap.hackaton.fiaptrip.reservas.repositories;

import br.com.fiap.hackaton.fiaptrip.reservas.models.DatasDisponiveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DatasDisponiveisRepository extends JpaRepository<DatasDisponiveis, Long> {
    DatasDisponiveis findByDate(LocalDate date);
}
