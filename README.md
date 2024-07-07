# Api de bilheteria para cinema (Java 🚀 com Spring Boot 🍃, Docker 🐳, MySQL 🐬 e Swagger)

Este é um projeto Java Spring boot, que consiste em uma API REST para registro de filmes, registro de sessões e registro de tickets, além de outras funções documentadas no swagger.

## Configuração

1. É necessário ter o JDK 21 e o docker instalados no PC.

2. Clone o repositório para sua máquina local:

    ```bash
    git clone https://github.com/Thullyoo/bilheteria-api
    ```

3. Navegue até o diretório do projeto:

    ```bash
    cd user-microservice
    ```

4. Instale as dependências com o maven


5. Abra a pasta do docker no terminal e digite:
     ```bash
    docker compose up
    ```

6. Inicia a aplicação


A aplicação estará rodando em `http://localhost:8082`. 

Swagger estará disponível no link: `http://localhost:8082/swagger-ui/index.html`

### Endpoints

  
- `POST /movies`: Registra filme.
- `GET /movies`: Retorna todos os filmes registrados. 
- `GET /movies/{id}`: Retorna filme pelo seu id.
- `PUT /movies/{id}`: Atualiza dados de um filme.
- `DELETE /movies/{id}`: Remove um filme registrado.

 
- `POST /movies/{movie_id}/sessions`: Registra uma sessão.
- `GET /movies/{movie_id}/sessions`: Retorna todas as sessões de um filme.
- `PUT /movies/{movie_id}/sessions/{session_id}`: Atualiza uma sessão. 

  
- `POST /movies/{movie_id}/sessions/{session_id}/tickets`: Registrar um ticket.

### Formato dos dados

As requisições e respostas são no formato JSON. Exemplo de como se deve passar o JSON no POST:

POST /movies:

```json
{
  "image": "string",
  "name": "string",
  "description": "string",
  "actors": [
    "string"
  ],
  "genre": "string",
  "release_date": "string"
}
```

PUT /movies/{id}:

```json
{
  "image": "string",
  "name": "string",
  "description": "string",
  "actors": [
    "string"
  ],
  "genre": "string",
  "release_date": "string"
}
```

POST /movies/{movie_id}/sessions

```json
{
  "room": "string",
  "capacity": 0,
  "day": "string",
  "time": "string"
}
```

PUT /movies/{movie_id}/sessions/{session_id}

```json
{
  "room": "string",
  "capacity": 0,
  "day": "string",
  "time": "string"
}
```

POST /movies/{movie_id}/sessions/{session_id}/tickets

```json
{
  "chair": "string",
  "value": 0
}
```


