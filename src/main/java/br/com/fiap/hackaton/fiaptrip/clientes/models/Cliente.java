package br.com.fiap.hackaton.fiaptrip.clientes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeComleto;
    private Locale paisOrigem;
    private LocalDate dataNascimento;
    private String cpf;
    private String passaporte;
    private String telefone;
    private String email;
    private String endereco;
//    @OneToMany
//    private Set<Reservas> reservas;

    public Cliente(String nomeComleto, Locale paisOrigem, LocalDate dataNascimento,
                   String cpf, String passaporte, String telefone, String email, String endereco) {
        this.nomeComleto = nomeComleto;
        this.paisOrigem = paisOrigem;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.passaporte = passaporte;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public void update(ClienteDTO clienteDTO, LocalDate dataDeNascimento, Locale paisOrigem) {
        if (!isEmpty(clienteDTO.nomeComleto())) {
            this.nomeComleto = clienteDTO.nomeComleto();
        }
        if (!isEmpty(paisOrigem)) {
            this.paisOrigem = paisOrigem;
        }
        if (!isEmpty(dataDeNascimento)) {
            this.dataNascimento = dataDeNascimento;
        }
        if (!isEmpty(clienteDTO.cpf())) {
            this.cpf = clienteDTO.cpf();
        }
        if (!isEmpty(clienteDTO.passaporte())) {
            this.passaporte = clienteDTO.passaporte();
        }
        if (!isEmpty(clienteDTO.telefone())) {
            this.telefone = clienteDTO.telefone();
        }
        if (!isEmpty(clienteDTO.email())) {
            this.email = clienteDTO.email();
        }
        if (!isEmpty(clienteDTO.telefone())) {
            this.endereco = clienteDTO.endereco();
        }
    }

    public ClienteDTO toClienteDTO() {
        return new ClienteDTO(
                this.id, this.nomeComleto, this.paisOrigem.getDisplayCountry(), this.dataNascimento.toString(),
                this.cpf, this.passaporte, this.telefone, this.email, this.endereco
        );
    }
}
