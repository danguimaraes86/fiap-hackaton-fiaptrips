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

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


--------------------------------------------------------------------------------------------
### Reservas

| Método | Url                   | Descrição               |
|--------|-----------------------|-------------------------|
| GET    | /reservas/{reservaId} | Get Reserva Por ID      |
| PUT    | /reservas/{reservaId} | Atualiza Reserva Por ID |
| DELETE | /reservas/{reservaId} | Deleta Reserva Por ID   |
| POST   | /reservas             | Cria nova Reserva       |
| GET    | /reservas             | Get All Reservas        |
| GET    | /reservas/{clienteID} | Get Reserva Por Cliente |


### Exemplos de entrada

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


--------------------------------------------------------------------------------------------
### Quartos

| Método | Url                 | Descrição                                              |
|--------|---------------------|--------------------------------------------------------|
| GET    | /quartos/{quartoId} | Get Quarto Por ID                                      |
| PUT    | /quartos/{quartoId} | Atualiza Qaurto Por ID                                 |
| DELETE | /quartos/{quartoId} | Deleta Quarto Por ID                                   |
| POST   | /quartos/novo       | Cria Novo Quarto                                       |
| GET    | /quartos            | Get All Qaurtos                                        |
| GET    | /quartos/tipoquarto | Categorias de Quarto Disponiveis ex: "Luxo","Standard" |
| GET    | /quartos/busca      | Get Quarto Por Categoria ex: "Luxo","Standard"         |
| GET    | /quartos/amenidades | Get Amenidades disponiveis ex: "TV","Poltrona"         |


### Exemplos de entrada

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
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

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
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

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


--------------------------------------------------------------------------------------------
### Adicionais

| Método | Url                       | Descrição                  |
|--------|---------------------------|----------------------------|
| PUT    | /Adicionais/{adicionalId} | Edita Adicional Por ID     |
| DELETE | /Adicionais/{adicionalId} | Delete Adicional por ID    |
| POST   | /Adicionais/novo          | Cria novo Adicional        |
| GET    | /Adicionais               | Get All Adicionais         |
| GET    | /Adicionais/busca         | Get Adiconal Por Descrição |


### Exemplos de entrada

##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```


##### <a id="updateEndereco">PUT - /enderecos/{id}</a>
![Uses Curl](https://img.shields.io/badge/Curl-Uses-green)
```bash
curl -X POST \
  https://api.example.com/endpoint \
  -H 'Content-Type: application/json' \
  -d '{
    "key": "value"
  }'
```

