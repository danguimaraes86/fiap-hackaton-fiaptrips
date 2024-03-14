package br.com.fiap.hackaton.fiaptrip.reservas.repositories;

import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    Page<Reserva> findAllByCliente_Id(Pageable pageable, Long clienteId);

    List<Reserva> findAllByQuartos(List<Quarto> quartoList);

    List<Reserva> findByQuartos_Id(Long id);
}
