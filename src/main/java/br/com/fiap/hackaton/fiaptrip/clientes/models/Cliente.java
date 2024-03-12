package br.com.fiap.hackaton.fiaptrip.clientes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

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
    private String paisOrigem;
    private LocalDate dataNascimento;
    private String cpf;
    private String passaporte;
    private String telefone;
    private String email;
    private String endereco;

    public Cliente(String nomeComleto, String paisOrigem, LocalDate dataNascimento,
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

    public void update(ClienteDTO clienteDTO) {
        if (!isEmpty(clienteDTO.nomeComleto())) {
            this.nomeComleto = clienteDTO.nomeComleto();
        }
        if (!isEmpty(clienteDTO.paisOrigem())) {
            this.paisOrigem = clienteDTO.paisOrigem();
        }
        if (!isEmpty(clienteDTO.dataNascimento())) {
            this.dataNascimento = clienteDTO.dataNascimento();
        }
        if (!isEmpty(clienteDTO.cpf())) {
            this.cpf = clienteDTO.cpf().orElse(null);
        }
        if (!isEmpty(clienteDTO.passaporte())) {
            this.passaporte = clienteDTO.passaporte().orElse(null);
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

    public ClienteDTO convertToDTO() {
        return new ClienteDTO(
                this.id, this.nomeComleto, this.paisOrigem, this.dataNascimento,
                Optional.of(this.cpf), Optional.of(this.passaporte), this.telefone, this.email, this.endereco
        );
    }
}
