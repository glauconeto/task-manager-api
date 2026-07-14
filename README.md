# Task Manager API

## 🎯 Objetivo
Desenvolver uma **API REST** para gerenciamento de tarefas, com autenticação segura, persistência de dados e documentação interativa. O projeto serve como estudo prático de **Spring Boot**, **JWT**, **PostgreSQL**, **Docker** e boas práticas de arquitetura de software.

---

## 🛠 Stack
   Categoria          | Tecnologias/ Ferramentas                          |
 |--------------------|--------------------------------------------------|
 | **Backend**        | Java 21, Spring Boot 4.1.0                       |
 | **Autenticação**   | JWT (JSON Web Tokens)                            |
 | **Banco de Dados** | PostgreSQL 17 (Docker)                           |
 | **ORM**            | Spring Data JPA, Hibernate                       |
 | **Validação**      | Spring Validation, Anotações customizadas       |
 | **Mapeamento**     | MapStruct 1.6.3                                  |
 | **Documentação**   | Swagger/OpenAPI (a ser integrado)                |
 | **Build**          | Gradle (KTS)                                     |
 | **Testes**         | JUnit 5, Spring Boot Test                        |
 | **Infra**          | Docker, Docker Compose                           |
 | **Boilerplate**    | Lombok                                           |

---

## 🏗 Arquitetura
### Camadas

```
src/main/java/io/github/glauconeto/taskmanager/
├── config/          # Configurações (Security, Swagger, etc.)
├── controller/      # Endpoints REST
├── dto/             # DTOs (Request/Response)
├── entity/          # Entidades JPA
├── exception/       # Tratamento de exceções globais
├── mapper/          # Mapeamento entre DTOs e Entidades (MapStruct)
├── repository/      # Repositórios JPA
├── service/         # Regras de negócio
└── validation/      # Validações customizadas
```

### Fluxo de Dados
1. **Controller**: Recebe requisições HTTP e delega para o `Service`.
2. **Service**: Contém a lógica de negócio e interage com o `Repository`.
3. **Repository**: Acessa o banco de dados via JPA.
4. **Mapper**: Converte entre `Entity` e `DTO` (usando MapStruct).
5. **DTO**: Objetos de transferência de dados para requisições/respostas.
6. **Exception**: Tratamento centralizado de erros (ex: `@ControllerAdvice`).

### Autenticação
- **JWT**: Token gerado no login, validado em um filtro (`OncePerRequestFilter`).
- **Segurança**: Configuração via `SecurityConfig` (Spring Security).

---
## 🗺 Roadmap
| Fase               | Descrição                                                                 | Status      |
|--------------------|---------------------------------------------------------------------------|-------------|
| **Estrutura Base** | Configuração inicial (Gradle, Docker, Spring Boot)                      | ✅ Concluído |
| **Entidades**      | Modelagem das entidades (ex: `Task`, `User`)                              | ✅ Concluído |
| **DTOs**           | Criação de DTOs para requisições/respostas                               | ✅ Concluído |
| **Persistência**   | Configuração e integração com **PostgreSQL** (Docker)                    | ✅ Concluído |
| **Camada Repository** | Implementação dos repositórios **Spring Data JPA**                   | ✅ Concluído |
| **Serviços**       | Lógica de negócio (CRUD, validações)                                      | ✅ Concluído |
| **Controladores**  | Endpoints REST (ex: `/tasks`, `/auth`)                                   | ✅ Concluído |
| **Conexão com Banco**   | Integração com banco de dados postgres                           | ⏳ Em andamento |
| **Autenticação**   | Integração com JWT (login, validação de token)                           | ⏳ Em andamento |
| **Validações**     | Validações com **Bean Validation** (`@Valid`, `@NotBlank`, etc.)         | ⏳ Em andamento |
| **Códigos HTTP**   | Uso correto de status codes (**201, 204, 400, 404, 409**, etc.)          | ⏳ Em andamento |
| **Tratamento de Erros** | Exceções globais com **`@ControllerAdvice`** e mensagens personalizadas | ❌ Pendente   |
| **Swagger**        | Documentação interativa da API                                           | ❌ Pendente   |
| **Testes**         | Testes unitários e de integração (JUnit, Mockito)                        | ❌ Pendente   |
| **Deploy**         | Configuração para deploy (ex: Dockerfile, CI/CD)                         | ❌ Pendente   |