package br.com.fiap.hackaton.fiaptrip.Quartos.Repositories;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface QuartoRepository extends JpaRepository<Quarto, UUID> {

}
