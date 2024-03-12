package br.com.fiap.hackaton.fiaptrip.Quartos.Repositories;

import br.com.fiap.hackaton.fiaptrip.Quartos.Models.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuartoRepository extends JpaRepository<Quarto, UUID> {

}
