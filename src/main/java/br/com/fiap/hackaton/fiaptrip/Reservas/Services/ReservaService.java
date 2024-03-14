package br.com.fiap.hackaton.fiaptrip.reservas.services;

import br.com.fiap.hackaton.fiaptrip.clientes.models.Cliente;
import br.com.fiap.hackaton.fiaptrip.clientes.services.ClienteService;
import br.com.fiap.hackaton.fiaptrip.quartos.models.Quarto;
import br.com.fiap.hackaton.fiaptrip.quartos.services.QuartoService;
import br.com.fiap.hackaton.fiaptrip.reservas.models.Reserva;
import br.com.fiap.hackaton.fiaptrip.reservas.models.dtos.ReservaDTO;
import br.com.fiap.hackaton.fiaptrip.reservas.repositories.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteService clienteService;
    private final QuartoService quartoService;

    public Page<Reserva> findAllReservas(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public Reserva findReservaById(UUID reservaId) {
        return reservaRepository.findById(reservaId).orElseThrow(
                () -> new NoSuchElementException(format("reserva_id [%s] não encontrada", reservaId))
        );
    }

    public Page<Reserva> findReservaByCliente(Pageable pageable, Long clienteId) {
        return reservaRepository.findAllByCliente_Id(pageable, clienteId);
    }

    public Reserva createReserva(ReservaDTO reservaDTO) {
        Cliente cliente = clienteService.findClienteByEmail(reservaDTO.clienteEmail());
        List<Quarto> quartosList = quartoService.findListQuartoById(reservaDTO.quartos());
        LocalDate dataCheckIn = getLocalDate(reservaDTO.dataCheckIn());
        LocalDate dataCheckOut = getLocalDate(reservaDTO.dataCheckOut());

        validarDatasQuarto(quartosList, dataCheckIn, dataCheckOut);

        Reserva reserva = new Reserva(cliente, quartosList, dataCheckIn, dataCheckOut);
        return reservaRepository.save(reserva);
    }

    //[TODO] updateReservas

    public void deleteReservaById(UUID reservaId) {
        reservaRepository.delete(
                reservaRepository.findById(reservaId).orElseThrow(
                        () -> new NoSuchElementException(format("reserva_id [%s] não encontrada", reservaId))
                ));
    }

    private void validarDatasQuarto(List<Quarto> quartosList, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        if(dataCheckIn.isBefore(LocalDate.now()) || dataCheckOut.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("data_checkIn/data_checkOut não pode ser anterior a data de hoje");
        }

        if (dataCheckOut.isBefore(dataCheckIn)) {
            throw new IllegalArgumentException("data_checkOut não pode ser anterior a data_checkIn");
        }
        quartosList.forEach(quarto -> reservaRepository.findByQuartos_Id(quarto.getId()).forEach(
                reserva -> {
                    if (dataCheckIn.isAfter(reserva.getDataCheckIn())
                            && dataCheckIn.isBefore(reserva.getDataCheckOut())
                            || dataCheckOut.isAfter(reserva.getDataCheckIn())
                    ) {
                        throw new IllegalArgumentException(format("data já está reservada para o quarto %d", quarto.getId()));
                    }
                })
        );
    }

    private LocalDate getLocalDate(String dataReserva) {
        return LocalDate.parse(dataReserva);
    }
}
