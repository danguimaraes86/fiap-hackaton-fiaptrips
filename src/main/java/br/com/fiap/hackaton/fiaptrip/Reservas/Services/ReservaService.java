package br.com.fiap.hackaton.fiaptrip.Reservas.Services;

import br.com.fiap.hackaton.fiaptrip.Reservas.Models.Reservas;
import br.com.fiap.hackaton.fiaptrip.Reservas.Repositories.ReservasRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    private final ReservasRepository reservasRepository;
    private final DatasDisponiveisServices datasDisponiveisServices;
    public ReservaService(ReservasRepository reservasRepository, DatasDisponiveisServices datasDisponiveisServices) {
        this.reservasRepository = reservasRepository;
        this.datasDisponiveisServices = datasDisponiveisServices;
    }

    // <>--------------- Metodos ---------------<>
    public Reservas criarReserva(Reservas reserva) {

        // Verifica se a data está disponível
        boolean estaDisponivel =
                datasDisponiveisServices.verificarDisponibilidade(reserva.getDataCheckIn(), reserva.getDataCheckOut());
        if (!estaDisponivel) throw new RuntimeException("Data não disponível");

        // Indisponibiliza as data
        datasDisponiveisServices.indisponibilizarDatas(reserva.getDataCheckIn(), reserva.getDataCheckOut());

        // Verifica se a data está disponível - Não Devem estar disponíveis
        boolean estaDisponivelNaoDeveEstarDispnivel =
                datasDisponiveisServices.verificarDisponibilidade(reserva.getDataCheckIn(), reserva.getDataCheckOut());
        if (estaDisponivelNaoDeveEstarDispnivel) throw new RuntimeException("Algum erro ocorreu, tente novamente!");

        return reservasRepository.save(reserva);
    }

    public Reservas atualizarReserva(Long id, Reservas reserva) {
        var reservaFound = reservasRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservaFound.setDataCheckIn(reserva.getDataCheckIn());
        reservaFound.setDataCheckOut(reserva.getDataCheckOut());
        reservaFound.setItensAdicionais(reserva.getItensAdicionais());
        reservaFound.calcularPrecoFinal();

        return reservasRepository.save(reservaFound);
    }

    public void deletarReserva(Long id) {
        reservasRepository.deleteById(id);
    }

}
