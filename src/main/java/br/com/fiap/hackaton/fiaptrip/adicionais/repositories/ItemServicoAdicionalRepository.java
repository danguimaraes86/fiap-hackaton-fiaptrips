package br.com.fiap.hackaton.fiaptrip.adicionais.repositories;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.ItemServicoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemServicoAdicionalRepository extends JpaRepository<ItemServicoAdicional, Long> {
    Optional<ItemServicoAdicional> findItemServicoAdicionalByDescricaoContainingIgnoreCase(String descricao);
}
