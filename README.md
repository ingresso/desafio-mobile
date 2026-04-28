# 🎟️ Meu Ingresso - Android

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-1.6+-green.svg)
![Architecture](https://img.shields.io/badge/Architecture-Clean_Architecture-orange.svg)
![Testing](https://img.shields.io/badge/Tests-Unit_%26_UI-brightgreen.svg)

**Meu Ingresso** é uma aplicação de alto desempenho voltada para o setor de entretenimento e cinema. O projeto foi concebido sob princípios rigorosos de engenharia de software, focando em **escalabilidade**, **manutenibilidade** e **testabilidade**, utilizando as tecnologias mais recentes do ecossistema Android moderno.

---

## 🏛️ Arquitetura e Engenharia

O projeto implementa **Clean Architecture** para garantir o desacoplamento entre a lógica de negócio e os frameworks de infraestrutura (UI, Banco de Dados, Rede).



### Camadas do Projeto:
* **`domain`**: Camada central (Kotlin puro). Contém as entidades de negócio, as interfaces dos repositórios e os *Use Cases* (Interactors). Não possui dependências do Android.
* **`data`**: Implementa as interfaces de repositório da camada de domínio. Gerencia o fluxo de dados entre fontes locais (Room) e remotas (Retrofit), implementando o padrão *Single Source of Truth*.
* **`presentation`**: Camada de UI utilizando **Jetpack Compose**. Implementa o padrão **MVVM (Model-View-ViewModel)** aliado ao **MVI (Model-View-Intent)** para garantir um fluxo de dados unidirecional (**UDF**).

---

## 🛠️ Stack Tecnológica

### Core e UI
* **Jetpack Compose:** UI 100% declarativa com **Material Design 3**.
* **Kotlin Coroutines & Flow:** Gestão de concorrência e fluxos de dados reativos.
* **Navigation Compose:** Navegação *type-safe* entre telas.
* **Coil:** Carregamento de imagens otimizado para Compose com suporte a cache.

### Injeção de Dependência e Dados
* **Koin:** Framework de DI pragmático para gestão de dependências e escopos de ViewModel.
* **Retrofit & OkHttp:** Consumo de APIs REST com interceptores de log e tratamento de erros.
* **Room Database:** Persistência local para suporte offline e cache de dados.

---

## 🧪 Estratégia de Qualidade (Testes)

O projeto segue a pirâmide de testes para garantir a entrega de código resiliente:

### Testes Unitários (`test`)
* Validação de **Use Cases** e lógica de negócio.
* Teste de estados de **ViewModels** utilizando `StateFlow` e mocks de repositórios.

### Testes Instrumentados (`androidTest`)
* **UI Tests:** Interação com a árvore de semântica do Compose.
* **Navigation Tests:** Uso de `TestNavHostController` para validar a integridade dos grafos de navegação.
* **State Hoisting:** Todos os Composables foram desenhados para permitir injeção de estados e controladores, facilitando o isolamento durante os testes de UI.

---

## 🏗️ Padrões de Projeto e Princípios Aplicados

* **SOLID:** Rigorosa aplicação para facilitar a extensão do sistema sem modificar o código existente.
* **Dependency Inversion:** Dependência de abstrações, facilitando a troca de implementações (ex: trocar API real por Mock).
* **State Hoisting:** Elevação de estado em componentes de UI para torná-los "puros" e testáveis.
* **Mappers:** Conversão de modelos de API (DTOs) para modelos de Domínio, protegendo o app de mudanças externas na API.

---

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/DannielRoque/desafio-mobile.git
    ```
2.  **Configuração de API:**
    Este projeto utiliza a API 
    GET https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio
    ```
3.  **Build:**
    Execute o Build no Android Studio ou via terminal:
    ```bash
    ./gradlew assembleDebug
    ```

---

## 👨‍💻 Desenvolvedor

**Daniel Roque**
*Senior Android Developer | Mobile Software Engineer*

Com mais de 6 anos de experiência no ecossistema Android, sou especialista em arquiteturas modernas, Jetpack Compose e desenvolvimento de aplicativos escaláveis com foco em performance e experiência do usuário.

---