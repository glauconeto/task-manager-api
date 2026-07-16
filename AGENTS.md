# AGENTS.md - Task Manager API

Java 21 / Spring Boot 4.1.0 REST API. Projeto de Módulo único do Gradle (Kotlin DSL, wrapper 9.5.1).
PostgreSQL 17 (dentro do docker), MapStruct 1.6.3 codegen, Lombok.

## Regra de Edição

**Confirme apenas com o usuário caso o comando seja vago demais.** Antes de modificar código:
1. Descreva **o que** será editado (arquivo, linha, trecho)
2. Descreva **por quê** (qual problema ou objetivo)
3. Aguarde confirmação explícita antes de executar

## Comandos

```bash
./gradlew build -x test          # Build sem testes (usado pelo Docker)
./gradlew build                  # Build com testes
./gradlew clean build            # Limpar + build
./gradlew test                   # Rodar todos os testes (requer PostgreSQL)
./gradlew bootRun                # Iniciar app
docker-compose up --build        # Stack completo (app + PostgreSQL)
docker-compose up postgres       # Iniciar apenas PostgreSQL
```

Execute apenas uma classe de teste:
```bash
./gradlew test --tests "io.github.glauconeto.taskmanager.TaskManagerApplicationTests"
```

Sem ferramentas de linting, formatação ou check de tipagem configuradas. Siga o estilo de código existente.

## Arquitetura

```
Controller --> Service (interface) --> ServiceImpl --> Repository --> PostgreSQL
                  |                                              |
               MapStruct                                    Hibernate
                  |                                              |
               DTOs (request/response)                      Entities
```

**Entrypoint:** `src/main/java/io/github/glauconeto/taskmanager/TaskManagerApplication.java`

**Pacotes** (sob `io.github.glauconeto.taskmanager`):
- `controller/` - Endpoints REST (`/tasks`, `/users`)
- `dto/request/` - DTOs de entrada com validação Jakarta
- `dto/response/` - DTOs de saída incluindo `ErrorResponse`
- `entity/` - Entidades JPA (PKs UUID) + enums (`TaskStatus`, `TaskPriority`)
- `exception/` - Exceções customizadas + `GlobalExceptionHandler`
- `mapper/` - Interfaces MapStruct (impls geradas em compile-time)
- `repository/` - Repositórios Spring Data JPA
- `service/` - Interfaces
- `service/impl/` - Implementações

## Observações Importantes

1. **PostgreSQL obrigatório para testes.** Sem testcontainers. Inicie com `docker-compose up postgres` antes de rodar testes.

2. **MapStruct codegen em compile-time.** Após editar interfaces `@Mapper`, execute `./gradlew compileJava` antes de usar o app. Fontes geradas: `build/generated/sources/`.

3. **Lombok + MapStruct coexistem.** Ambos configurados como annotation processors no `build.gradle.kts`. Entities usam `@Getter`/`@Setter`/`@RequiredArgsConstructor`.

4. **Security incompleta.** `SecurityConfig` só fornece bean `PasswordEncoder`. Sem `SecurityFilterChain` ou JWT. A auto-config do Spring Security pode bloquear endpoints.

5. **Hibernate DDL-auto.** Schema auto-gerenciado (`ddl-auto: update`). Sem Flyway/Liquibase.

6. **Variáveis de ambiente.** Config parametrizada via env vars com defaults no `application.yml`. Docker Compose usa `.env` (gitignored); ver `.env.example`.

7. **Primary keys UUID.** Todas as entities usam `GenerationType.UUID`.

8. **Tratamento de erros.** `GlobalExceptionHandler` captura: `ResourceNotFoundException` (404), `BusinessException` (400), `MethodArgumentNotValidException` (400 com erros de campo).

9. **Convenções REST.** Controllers retornam `ResponseEntity<T>`. Criação = 201, Delete = 204.

## Testes

- Apenas um teste: `TaskManagerApplicationTests.contextLoads()` (carregamento do contexto Spring Boot).
- Usa `@SpringBootTest` (inicia contexto completo, precisa de PostgreSQL).
- JUnit 5 via `useJUnitPlatform()`.
- Sem perfis de teste, sem framework de mocking além do Spring Boot Test.

## Arquivos de Build

- `build.gradle.kts` - Config principal do build (dependências, plugins, annotation processors)
- `settings.gradle.kts` - Configurações do projeto
- `gradle/wrapper/gradle-wrapper.properties` - Fixa Gradle 9.5.1
- `docker-compose.yml` - PostgreSQL + serviços da app
- `Dockerfile` - Build multi-stage (Alpine), pula testes
- `application.yml` - Config de runtime com fallbacks de env vars
- `.env.example` - Template para variáveis de ambiente obrigatórias
