package br.com.fiap.hackaton.fiaptrip.reservas.services;

import br.com.fiap.hackaton.fiaptrip.adicionais.models.Adicional;
import br.com.fiap.hackaton.fiaptrip.adicionais.service.AdicionalService;
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
import java.util.*;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class ReservaService {

    public static final String RESERVA_NAO_ENCONTRADA = "reserva_id [%s] não encontrada";
    private final ReservaRepository reservaRepository;
    private final ClienteService clienteService;
    private final QuartoService quartoService;
    private final AdicionalService adicionalService;

    public Page<Reserva> findAllReservas(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public Reserva findReservaById(UUID reservaId) {
        return reservaRepository.findById(reservaId).orElseThrow(
                () -> new NoSuchElementException(format(RESERVA_NAO_ENCONTRADA, reservaId))
        );
    }

    public Page<Reserva> findReservaByClienteId(Pageable pageable, Long clienteId) {
        return reservaRepository.findAllByCliente_Id(pageable, clienteId);
    }

    public Reserva createReserva(ReservaDTO reservaDTO) {
        Cliente cliente = getClienteByEmail(reservaDTO);
        List<Quarto> quartosList = getListQuartoById(reservaDTO);
        Map<Adicional, Long> listaAdicionais = getListaAdicionais(reservaDTO.adicionalList());

        LocalDate dataCheckIn = getLocalDate(reservaDTO.dataCheckIn());
        LocalDate dataCheckOut = getLocalDate(reservaDTO.dataCheckOut());

        validarDatasQuarto(quartosList, dataCheckIn, dataCheckOut);

        Reserva reserva = new Reserva(cliente, quartosList, dataCheckIn, dataCheckOut, listaAdicionais);
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(UUID reservaId, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(reservaId).orElseThrow(
                () -> new NoSuchElementException(format(RESERVA_NAO_ENCONTRADA, reservaId))
        );
        Cliente cliente = getClienteByEmail(reservaDTO);
        List<Quarto> quartosList = getListQuartoById(reservaDTO);
        Map<Adicional, Long> listaAdicionais = getListaAdicionais(reservaDTO.adicionalList());

        LocalDate dataCheckIn = getLocalDate(reservaDTO.dataCheckIn());
        LocalDate dataCheckOut = getLocalDate(reservaDTO.dataCheckOut());

        validarDatasQuarto(quartosList, dataCheckIn, dataCheckOut);
        reserva.update(cliente, quartosList, dataCheckIn, dataCheckOut, listaAdicionais);
        return reservaRepository.save(reserva);
    }

    public void deleteReservaById(UUID reservaId) {
        reservaRepository.delete(
                reservaRepository.findById(reservaId).orElseThrow(
                        () -> new NoSuchElementException(format(RESERVA_NAO_ENCONTRADA, reservaId))
                ));
    }

    private void validarDatasQuarto(List<Quarto> quartosList, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        if (dataCheckIn.isBefore(LocalDate.now()) || dataCheckOut.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("data_checkIn/data_checkOut não pode ser anterior a data de hoje");
        }

        if (dataCheckOut.isBefore(dataCheckIn)) {
            throw new IllegalArgumentException("data_checkOut não pode ser anterior a data_checkIn");
        }
        quartosList.forEach(quarto -> reservaRepository.findByQuartos_Id(quarto.getId()).forEach(
                reserva -> {
                    if ((dataCheckIn.isAfter(reserva.getDataCheckIn()) && dataCheckIn.isBefore(reserva.getDataCheckOut()))
                            || (dataCheckOut.isAfter(reserva.getDataCheckIn()) && dataCheckOut.isBefore(reserva.getDataCheckOut()))
                            || (dataCheckIn.isEqual(reserva.getDataCheckIn()) && dataCheckOut.isEqual(reserva.getDataCheckOut()))
                    ) {
                        throw new IllegalArgumentException(format("data já está reservada para o quarto %d", quarto.getId()));
                    }
                })
        );
    }

    private Cliente getClienteByEmail(ReservaDTO reservaDTO) {
        return clienteService.findClienteByEmail(reservaDTO.clienteEmail());
    }

    private List<Quarto> getListQuartoById(ReservaDTO reservaDTO) {
        return quartoService.findListQuartoById(reservaDTO.quartos());
    }

    private Map<Adicional, Long> getListaAdicionais(Map<String, Long> listaDTO) {
        Map<Adicional, Long> listaAdicionais = new HashMap<>();
        List<String> adicionalList = listaDTO.keySet().stream().toList();
        List<Adicional> adicionalListByDescricao = adicionalList.stream().map(adicionalService::findAdicionalByDescricao).toList();
        adicionalListByDescricao.forEach(adicional ->
                listaAdicionais.put(adicional, listaDTO.get(adicional.getDescricao()))
        );
        return listaAdicionais;
    }

    private LocalDate getLocalDate(String dataReserva) {
        return LocalDate.parse(dataReserva);
    }
}
