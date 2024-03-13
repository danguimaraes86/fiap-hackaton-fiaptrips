package br.com.fiap.hackaton.fiaptrip.Reservas.Services;

import br.com.fiap.hackaton.fiaptrip.Reservas.Models.DatasDisponiveis;
import br.com.fiap.hackaton.fiaptrip.Reservas.Repositories.DatasDisponiveisRepository;
import br.com.fiap.hackaton.fiaptrip.Reservas.Repositories.ReservasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class DatasDisponiveisServices {
    private final DatasDisponiveisRepository datasDisponiveisRepository;
    public DatasDisponiveisServices(DatasDisponiveisRepository datasDisponiveisRepository) {
        this.datasDisponiveisRepository = datasDisponiveisRepository;
    }


    // <>--------------- Metodos ---------------<>
    public boolean verificarDisponibilidade(LocalDate dataInicio, LocalDate dataFim){
        Set<DatasDisponiveis> entreCheckInCheckOut = datasEntreCheckInCheckOut(dataInicio, dataFim);

        // Aqui precisamos verificar se na data de inicio o checkIn está disponível
        DatasDisponiveis dataInicioDisponivel = datasDisponiveisRepository.findByDate(dataInicio);
        Boolean estaDisponivel = dataInicioDisponivel.isCheckInDisponivel();

        // Aqui precisamos verificar de as datas entre o checkIn e o checkOut estão disponíveis
        estaDisponivel = entreCheckInCheckOut.stream().anyMatch(x -> x.isCheckInDisponivel() && x.isCheckOutDisponivel());

        // Aqui precisamos verificar se na data de inicio o checkOut está disponível
        DatasDisponiveis dataFimDisponivel = datasDisponiveisRepository.findByDate(dataFim);
        estaDisponivel = dataFimDisponivel.isCheckOutDisponivel();

        return estaDisponivel;
   }

    public void indisponibilizarDatas(LocalDate dataInicio, LocalDate dataFim){


    }


    // <>--------------- Metodos Internos Auxiliares ---------------<>
    private Set<DatasDisponiveis> datasEntreCheckInCheckOut(LocalDate dataInicio, LocalDate dataFim){
        Set<DatasDisponiveis> datasEntre = new HashSet<>();

        LocalDate current = dataInicio.plusDays(1);
        while (!current.isAfter(dataFim.minusDays(1))) {
            DatasDisponiveis dataDisponibilidade = datasDisponiveisRepository.findByDate(current);
            datasEntre.add(dataDisponibilidade);

            current = current.plusDays(1);
        }

        return datasEntre;
    }
}
