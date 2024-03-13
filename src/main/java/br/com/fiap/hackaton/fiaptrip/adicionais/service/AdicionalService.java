package br.com.fiap.hackaton.fiaptrip.adicionais.service;

import br.com.fiap.hackaton.fiaptrip.adicionais.dto.ItensDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.dto.ServicosDTO;
import br.com.fiap.hackaton.fiaptrip.adicionais.model.Itens;
import br.com.fiap.hackaton.fiaptrip.adicionais.model.Servicos;
import br.com.fiap.hackaton.fiaptrip.adicionais.repositorie.ItensRepository;
import br.com.fiap.hackaton.fiaptrip.adicionais.repositorie.ServicosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AdicionalService {


    private final ItensRepository itensRepository;

    private final ServicosRepository servicosRepository;

    public Optional<Itens> getItemById(Long id){
        return itensRepository.findById(id);
    }

    public Optional<Servicos> getServicoById(Long id){
        return servicosRepository.findById(id);
    }

    public Itens createItem(ItensDTO itensDTO){
        Itens item = new Itens(itensDTO.valor(), itensDTO.servicoItem(), itensDTO.itensEnum());
        return itensRepository.save(item);
    }

    public Servicos createService(ServicosDTO servicosDTO){
        Servicos servicos = new Servicos(servicosDTO.valor(), servicosDTO.servicoItem(), servicosDTO.servicosEnum());
        return servicosRepository.save(servicos);
    }

    public void deleteItem(Long id){
        itensRepository.delete(
                itensRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException(format("item_id [%d] não encontrado", id))
                )
        );
    }

    public void deleteService(Long id){
        servicosRepository.delete(
                servicosRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException(format("servicos_id [%d] não encontrado", id))
                )
        );
    }

    public Itens updateItem(ItensDTO itensDTO, Long id){
        Optional<Itens> itensOptional = itensRepository.findById(id);
        Itens item = itensOptional.get();
        item.setValor(itensDTO.valor());
        item.setServicoItem(itensDTO.servicoItem());
        item.setItensEnum(itensDTO.itensEnum());
        return itensRepository.save(item);
    }

    public Servicos updateService(ServicosDTO servicosDTO, Long id){
        Optional<Servicos> servicosOptional = servicosRepository.findById(id);
        Servicos servico = servicosOptional.get();
        servico.setValor(servicosDTO.valor());
        servico.setServicoItem(servicosDTO.servicoItem());
        servico.setServicosEnum(servicosDTO.servicosEnum());
        return servicosRepository.save(servico);
    }
}
