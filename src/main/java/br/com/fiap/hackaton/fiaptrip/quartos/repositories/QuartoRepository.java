package br.com.fiap.hackaton.fiaptrip.quartos.repositories;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.models.enums.TipoQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    Optional<Quarto> findQuartoByTipoQuartoIgnoreCase(String tipoQuarto);
}
