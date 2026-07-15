# 🛡️ Spring Shift Left Security - Demo

Este projeto é uma aplicação de demonstração educacional criada para ilustrar o conceito de **Shift Left Security** na prática. O objetivo é mostrar como trazer a análise de segurança para as etapas iniciais do desenvolvimento (SDLC) previne que vulnerabilidades críticas cheguem aos ambientes de produção.

A aplicação contém falhas de segurança **intencionais** mapeadas pelo **OWASP Top 10**, ideais para testes com ferramentas de Static Application Security Testing (SAST).

---

## 🛠️ Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3.2.x** (Web, Data JPA)
*   **H2 Database** (Banco em memória)
*   **Springdoc OpenAPI** (Swagger)
*   **Semgrep** (Motor de análise SAST)

---

## 🚀 Pré-requisitos

Para rodar este laboratório localmente, você precisará ter instalado na sua máquina:
*   [Java 21 JDK](https://adoptium.net/)
*   [Apache Maven](https://maven.apache.org/)
*   [Semgrep](https://semgrep.dev/docs/getting-started/) (Recomendado instalar via `pipx install semgrep` no Linux/macOS)

---

## ⚙️ Como executar o projeto

### Opção 1: Via Terminal (Maven)
Navegue até a pasta raiz do projeto e execute:
```bash
mvn clean install
mvn spring-boot:run