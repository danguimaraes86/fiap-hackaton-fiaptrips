package br.com.fiap.hackaton.fiaptrip.adicionais.repositories;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long> {
    Optional<Adicional> findItemServicoAdicionalByDescricaoContainingIgnoreCase(String descricao);
}
