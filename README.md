# Projeto Movie

Este é um projeto simples de API RESTful para gerenciar filmes. A API permite executar operações CRUD em filmes. Além disso, ela fornece autenticação e autorização de usuários para proteger os endpoints sensíveis.

### Pré-requisitos

- Java Development Kit (JDK) 11 ou superior
- Maven
- Banco H2

## Endpoints

### Filmes

- **GET /movies**: Recupera uma lista paginada de todos os filmes.

- **GET /movies/{id}**: Recupera um filme específico pelo seu ID.

- **POST /movies**: Cria um novo filme. (Requer acesso ADMIN) *Campos name, genre, description são obrigatórios*
```
{
  "name": "Nome do Filme",
  "actors": "Ator 1, Ator 2",
  "director": "Diretor do Filme",
  "genre": "Gênero do Filme",
  "releaseDate": "2001-11-16",
  "description": "Descrição do Filme"
}
```
- **PUT /movies/{id}**: Atualiza um filme existente pelo seu ID. (Requer acesso ADMIN)

- **DELETE /movies/{id}**: Deleta um filme pelo seu ID. (Requer acesso ADMIN)

### Gêneros

- **GET /genres**: Recupera uma lista de todos os gêneros.
- **GET /genres/{drama}**: Recupera uma lista com os filmes do gênero drama.

### Autenticação de Usuário

- **POST /auth/login**: Autentica um usuário e gera um token JWT.

### Registro de Usuário

- **POST /auth/register**: Registra um novo usuário.

## Tratamento de Erros

A API possui tratamento de erros para cenários comuns, como erros de validação e recursos não encontrados. Se ocorrer algum erro,você receberá um código de resposta HTTP apropriado junto com uma mensagem de erro descritiva.

## Autorização
Certos pontos de extremidade (por exemplo, criar e excluir filmes) exigem que o usuário tenha a função "ADMIN". Para acessar esses endpoints, você precisa incluir o token JWT gerado no cabeçalho "Authorization" da solicitação HTTP usando o esquema "Bearer".
