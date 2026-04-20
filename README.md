# Ingresso.com - Desafio Android

Aplicação desenvolvida para o desafio técnico de **Desenvolvedor Android**, focada em exibir o catálogo "Em Breve" da Ingresso.com com uma experiência fluida, moderna e resiliente.

## 🚀 Arquitetura e Tech Stack

O projeto adota os princípios de **Clean Architecture** e **MVVM**, garantindo escalabilidade e facilidade de manutenção.

-   **Camadas**: 
    - `Domain`: Regras de negócio e Use Cases puros.
    - `Data`: Repositórios com estratégia **Offline-First** (Room + Retrofit).
    - `Presentation`: UI moderna em **Jetpack Compose** e Unidirectional Data Flow.
-   **DI**: Koin para injeção de dependência.
-   **Multithreading**: Coroutines e Flow para processamento assíncrono e reatividade.
-   **UX**: Dark theme nativo.

## 💎 Funcionalidades Principais

-   **Suporte Offline**: O Room atua como fonte única de verdade. O cache é sincronizado de forma inteligente, removendo dados obsoletos e preservando a experiência sem internet.
-   **Filtros Inteligentes**: Lista horizontal de gêneros extraída dinamicamente e integrada com a busca textual.
-   **Navegação Robusta**: Gerenciamento de backstack otimizado para evitar acúmulo de telas e suporte a deep flows.
-   **Observabilidade**: Logs estratégicos no repositório para monitoramento de sincronização e falhas de rede.
-   **Qualidade**: Cobertura de testes unitários em Use Cases, Mappers e ViewModels.

## 🛠️ Como Executar

1.  Clone o projeto.
2.  Abra no **Android Studio Hedgehog** ou superior.
3.  Configure o **Java 17** no Gradle.
4.  Certifique-se de usar um dispositivo/emulador com **API 26+**.
5.  Execute o app via Android Studio ou `./gradlew installDebug`.

---
**Desenvolvido por Otávio Schmitt**
