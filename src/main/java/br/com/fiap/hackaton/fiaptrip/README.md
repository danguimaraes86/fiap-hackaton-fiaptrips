# Tech Challenge 

Bem-vindo(a) a documentação oficial da API desenvolvida para o Hackaton. Nesta documentação, 
você encontrará detalhes abrangentes sobre a API Booking de Hospedagem para Hoteis. 
Esta API gerencia dados de agendamento de Reservas para Hoteis. Onde o Usuario pode escolher o Hotel desejado na 
data em que deseja viajar e Agendar sua estadia. 
Esta API faz o BackEnd do serviços.

## Tecnologias e Ferramentas
- Java 17
- Maven
- Spring Data JPA
- PstgreSQL
- Lombok
- Swagger
- Spring Web
- Postman
- IntelliJ
- Git
- GitHub

## Explore

### Torre

| Método | Url          | Descrição               | Modelo de Requisição válido |
|--------|--------------|-------------------------| ------------------------- |
| GET    | /torres/{id} | Get Torre Por ID        | [JSON](#createEletro)|
| PUT    | /torres/{id} | Atualiza Torre Por ID   |  |
| DELETE | /torres/{id} | Deleta Torre Por ID     | |
| GET    | /torres      | Get All Torres          | |
| POST   | /torres      | Cadastra uma nova Torre | |

### Exemplos de entrada

##### <a id="createEletro"> POST - /eletrodomestico</a>
```json
{
 "nome": "Geladeira",
 "potencia": "500w",
 "modelo": "Eletrolux",
 "fabricacao": "2019-12-09"
}
```

##### <a id="updateEletro">PUT - /eletrodomestico/{id}</a>
```json
{
 "nome": "Fogão",
 "potencia": "3000w",
 "modelo": "Brastemp",
 "fabricacao": "2019-12-09"
}
```
### Reservas

| Método | Url                   | Descrição               | Modelo de Requisição válido |
|--------|-----------------------|-------------------------| ------------------------- |
| GET    | /reservas/{reservaId} | Get Reserva Por ID      | [JSON](#createPessoa)|
| PUT    | /reservas/{reservaId} | Atualiza Reserva Por ID | [JSON](#updatepessoa) |
| DELETE | /reservas/{reservaId} | Deleta Reserva Por ID   | |
| POST   | /reservas             | Cria nova Reserva       | |
| GET    | /reservas             | Get All Reservas        | |
| GET    | /reservas/{clienteID} | Get Reserva Por Cliente | |

### Exemplos de entrada

##### <a id="createPessoa"> POST - /pessoa</a>
```json
{
 "nome": "Marcos Leonardo",
 "dataNascimento": "2003-05-02",
 "sexo": "Masculino",
 "parentesco": "Filho"
}
```

##### <a id="updatepessoa">PUT - /pessoa/{id}</a>
```json
{
 "nome": "Rodrigo Fernandez",
 "dataNascimento": "2003-05-02",
 "sexo": "Masculino",
 "parentesco": "Irmão"
}
```
### Quartos

| Método | Url                 | Descrição                                              | Modelo de Requisição válido |
|--------|---------------------|--------------------------------------------------------| ------------------------- |
| GET    | /quartos/{quartoId} | Get Quarto Por ID                                      | [JSON](#createEndereco)|
| PUT    | /quartos/{quartoId} | Atualiza Qaurto Por ID                                 | [JSON](#updateEndereco) |
| DELETE | /quartos/{quartoId} | Deleta Quarto Por ID                                   | |
| POST   | /quartos/novo       | Cria Novo Quarto                                       | |
| GET    | /quartos            | Get All Qaurtos                                        | |
| GET    | /quartos/tipoquarto | Categorias de Quarto Disponiveis ex: "Luxo","Standard" | |
| GET    | /quartos/busca      | Get Quarto Por Categoria ex: "Luxo","Standard"         | |
| GET    | /quartos/amenidades | Get Amenidades disponiveis ex: "TV","Poltrona"          | |

### Exemplos de entrada

##### <a id="createEndereco"> POST - /enderecos</a>
```json
{
 "nomeInstalacao": "Casa de Campo",
 "rua": "Rua Princesa Isabel",
 "numero": "70",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
```json
{
 "nomeInstalacao": "Escritório",
 "rua": "Rua Edson Arantes",
 "numero": "100",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

### Localidades

| Método | Url               | Descrição                  | Modelo de Requisição válido |
|--------|-------------------|----------------------------| ------------------------- |
| GET    | /localidades/{id} | Get Localidade Por ID      | [JSON](#createEndereco)|
| PUT    | /localidades/{id} | Atualiza Localidade Por ID | [JSON](#updateEndereco) |
| DELETE | /localidades/{id} | Deleta Localidade Por ID   | |
| GET    | /localidades      | Get All Localidades        | |
| POST   | /localidades      | Cria Localidade            | |

### Exemplos de entrada

##### <a id="createEndereco"> POST - /enderecos</a>
```json
{
 "nomeInstalacao": "Casa de Campo",
 "rua": "Rua Princesa Isabel",
 "numero": "70",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
```json
{
 "nomeInstalacao": "Escritório",
 "rua": "Rua Edson Arantes",
 "numero": "100",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

### Clientes

| Método | Url                   | Descrição               | Modelo de Requisição válido |
|--------|-----------------------|-------------------------| ------------------------- |
| PUT    | /clientes/{clienteId} | Atualiza Cliente Por ID | [JSON](#createEndereco)|
| DELETE | /clientes/{clienteId} | Deleta Cliente Por ID   | [JSON](#updateEndereco) |
| POST   | /clientes/novo        | Cria Cliente            | |
| GET    | /clientes             | Get All Clientes        | |
| GET    | /clientes/busca       | Get Por EMAIL           | |

### Exemplos de entrada

##### <a id="createEndereco"> POST - /enderecos</a>
```json
{
 "nomeInstalacao": "Casa de Campo",
 "rua": "Rua Princesa Isabel",
 "numero": "70",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
```json
{
 "nomeInstalacao": "Escritório",
 "rua": "Rua Edson Arantes",
 "numero": "100",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

### Adicionais   ////////  A FAZER

| Método | Url                       | Descrição                  | Modelo de Requisição válido |
|--------|---------------------------|----------------------------| ------------------------- |
| PUT    | /Adicionais/{adicionalId} | Edita Adicional Por ID     | [JSON](#createEndereco)|
| DELETE | /Adicionais/{adicionalId} | Delete Adicional por ID    | [JSON](#updateEndereco) |
| POST   | /Adicionais/novo          | Cria novo Adicional        | |
| GET    | /Adicionais               | Get All Adicionais         | |
| GET    | /Adicionais/busca         | Get Adiconal Por Descrição | |

### Exemplos de entrada

##### <a id="createEndereco"> POST - /enderecos</a>
```json
{
 "nomeInstalacao": "Casa de Campo",
 "rua": "Rua Princesa Isabel",
 "numero": "70",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
```json
{
 "nomeInstalacao": "Escritório",
 "rua": "Rua Edson Arantes",
 "numero": "100",
 "bairro": "Urbano Caldera",
 "cidade": "Santos",
 "estado": "São Paulo"
}
```


