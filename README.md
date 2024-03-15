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


--------------------------------------------------------------------------------------------
## Explore


--------------------------------------------------------------------------------------------
### Torre

| Método | Url          | Descrição               |
|--------|--------------|-------------------------|
| GET    | /torres/{id} | Get Torre Por ID        |
| PUT    | /torres/{id} | Atualiza Torre Por ID   |
| DELETE | /torres/{id} | Deleta Torre Por ID     |
| GET    | /torres      | Get All Torres          |
| POST   | /torres      | Cadastra uma nova Torre |


### Exemplos de entrada

##### <a id="updateEndereco">GET - /torres/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```

##### <a id="updateEndereco">PUT - /torres/{id}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /torres/{id}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">GET - /torres</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">POST - /torres</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```


--------------------------------------------------------------------------------------------
### Reservas

| Método | Url                           | Descrição                  |
|--------|-------------------------------|----------------------------|
| GET    | /reservas/{reservaId}         | Get Reserva Por ID         |
| PUT    | /reservas/{reservaId}         | Atualiza Reserva Por ID    |
| DELETE | /reservas/{reservaId}         | Deleta Reserva Por ID      |
| POST   | /reservas                     | Cria nova Reserva          |
| GET    | /reservas                     | Get All Reservas           |
| GET    | /reservas/cliente/{clienteId} | Get Reserva Por Cliente ID |


### Exemplos de entrada

##### <a id="updateEndereco">GET - /reservas/{reservaId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/reservas/970d66b4-16f1-46c0-8c79-b42ebd3a644d'
```

##### <a id="updateEndereco">PUT - /reservas/{reservaId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /reservas/{reservaId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash

```

##### <a id="updateEndereco">POST - /reservas</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/reservas/novo' \
--header 'Content-Type: application/json' \
--data-raw '{
  "clienteEmail": "Elinore.Wehner@example.com",
  "quartos": [
    1
  ],
  "dataCheckIn": "2024-04-23",
  "dataCheckOut": "2024-04-24",
  "adicionalList": {
    "café": 1,
    "refrigerante": 2
  }
}'
```

##### <a id="updateEndereco">GET - /reservas</a> 

![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/reservas'
```

##### <a id="updateEndereco">GET - /reservas/cliente/{clienteId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/reservas/cliente/2'
```


--------------------------------------------------------------------------------------------
### Quartos

| Método | Url                 | Descrição                                              |
|--------|---------------------|--------------------------------------------------------|
| GET    | /quartos/{quartoId} | Get Quarto Por ID                                      |
| PUT    | /quartos/{quartoId} | Atualiza Qaurto Por ID                                 |
| DELETE | /quartos/{quartoId} | Deleta Quarto Por ID                                   |
| POST   | /quartos/novo       | Cria Novo Quarto                                       |
| GET    | /quartos            | Get All Quartos                                        |
| GET    | /quartos/tipoquarto | Categorias de Quarto Disponiveis ex: "Luxo","Standard" |
| GET    | /quartos/busca      | Get Quarto Por Categoria ex: "Luxo","Standard"         |
| GET    | /quartos/amenidades | Get Amenidades disponiveis ex: "TV","Poltrona"         |


### Exemplos de entrada

##### <a id="updateEndereco">GET - /quartos/{quartoId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```


##### <a id="updateEndereco">PUT - /quartos/{quartoId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /quartos/{quartoId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">POST - /quartos/novo</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/quartos/novo' \
--header 'Content-Type: application/json' \
--data '{
  "tipoQuarto": "LUXO",
  "amenidades": [
    "TV",
    "AR_CONDICIONADO",
    "FRIGOBAR",
    "BANHEIRA",
    "MESA_ESCRITORIO",
    "VARANDA"
  ]
}'
```

##### <a id="updateEndereco">GET - /quartos</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/quartos'
```

##### <a id="updateEndereco">GET - /quartos/tipoquarto</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/quartos/tipoquarto'
```

##### <a id="updateEndereco">GET - /quartos/busca</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">GET - /quartos/amenidades</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/quartos/amenidades'
```

--------------------------------------------------------------------------------------------
### Localidades

| Método | Url               | Descrição                  |
|--------|-------------------|----------------------------|
| GET    | /localidades/{id} | Get Localidade Por ID      |
| PUT    | /localidades/{id} | Atualiza Localidade Por ID |
| DELETE | /localidades/{id} | Deleta Localidade Por ID   |
| GET    | /localidades      | Get All Localidades        |
| POST   | /localidades      | Cria Localidade            |


### Exemplos de entrada

##### <a id="updateEndereco">GET - /localidades/{id}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">PUT - /localidades/{id}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /localidades/{id}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">GET - /localidades</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">POST - /localidades</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```


--------------------------------------------------------------------------------------------
### Clientes

| Método | Url                   | Descrição               |
|--------|-----------------------|-------------------------|
| PUT    | /clientes/{clienteId} | Atualiza Cliente Por ID |
| DELETE | /clientes/{clienteId} | Deleta Cliente Por ID   |
| POST   | /clientes/novo        | Cria Cliente            |
| GET    | /clientes             | Get All Clientes        |
| GET    | /clientes/busca       | Get Por EMAIL           |


### Exemplos de entrada

##### <a id="updateEndereco">PUT - /clientes/{clienteId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /clientes/{clienteId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">POST - /clientes/novo</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/clientes/novo' \
--header 'Content-Type: application/json' \
--data-raw '{
  "nomeComleto": "Mr. Brandy Durgan",
  "paisOrigem": "pt_br",
  
  "dataNascimento": "2000-01-01",
  "cpf": "11122233344",
  "passaporte": "XXX-8765",
  "telefone": "55-61-9999-8888",
  "email": "Gail.Ziemann@example.com",
  "endereco": "182 Kohler Drive"
}'
```

##### <a id="updateEndereco">GET - /clientes</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/clientes'
```

##### <a id="updateEndereco">GET - /clientes/busca</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```


--------------------------------------------------------------------------------------------
### Adicionais

| Método | Url                       | Descrição                  |
|--------|---------------------------|----------------------------|
| PUT    | /adicionais/{adicionalId} | Edita Adicional Por ID     |
| DELETE | /adicionais/{adicionalId} | Delete Adicional por ID    |
| POST   | /adicionais/novo          | Cria novo Adicional        |
| GET    | /adicionais               | Get All Adicionais         |
| GET    | /adicionais/busca         | Get Adiconal Por Descrição |


### Exemplos de entrada

##### <a id="updateEndereco">PUT - /adicionais/{adicionalId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">DELETE - /adicionais/{adicionalId}</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```

##### <a id="updateEndereco">POST - /adicionais/novo</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/adicionais/novo' \
--header 'Content-Type: application/json' \
--data '{
  "descricao": "massagem",
  "valor": 5.0,
  "tipoAdicional": "servico"
}'
```

##### <a id="updateEndereco">GET - /adicionais</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl --location 'localhost:3000/adicionais'
```

##### <a id="updateEndereco">GET - /adicionais/busca</a>
![Uses Curl](images/Curl-Uses-green.svg)
```bash
curl -X 
```