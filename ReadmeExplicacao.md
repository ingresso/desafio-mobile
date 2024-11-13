DesafioIngressoApp - Hector Fortuna Suárez / Teste Android Jr

    Funcionalidades Principais
    Arquitetura
    Estrutura do Projeto
    Dependências Externas
    Funcionalidades
    Como Executar
    Testes
  
    
Funcionalidades Principais:

    Shimmer para indicar carregamento enquanto os filmes são recuperados.
    Busca Dinâmica que permite pesquisar filmes letra por letra.
    Filtro de Filmes que mostra apenas os filmes do mês atual, com um ícone de calendário.
    Lista de Filmes com RecyclerView em grid (3 colunas), exibindo o poster e o nome.
    Detalhes do Filme: Ao clicar em um filme, o usuário é redirecionado para uma tela com informações detalhadas como sinopse, elenco, gênero, duração e um botão para compartilhar a URL do filme.
    Filmes em pré-venda são destacados com cor laranja.

Arquitetura

O projeto segue a arquitetura MVVM (Model-View-ViewModel) e Clean Architecture, separando as responsabilidades em camadas para uma maior modularidade e testabilidade.
Camadas:

    Core: Contém os State e Status, que são usados nas ViewModels para representar os estados da UI (sucesso, erro, carregando).
    Data: Responsável pelos modelos de dados (Model) e pela comunicação com a API(network).
        Model: Contém a estrutura de dados dos filmes, como nome, poster, data de lançamento, etc.
        Network: Contém as classes para realizar as chamadas HTTP via Retrofit.
        Repository: Lida com a lógica de negócios, reunindo dados de várias fontes (API, cache, etc.).
    DI (Dependency Injection): Utiliza o Dagger Hilt para gerenciar a injeção de dependências.
        Modules: Configurações do Retrofit, Repository.
        Coroutine: Configurações específicas para o uso de coroutines com Hilt.
    View: Responsável pela interface do usuário, incluindo Fragments, ViewModels e Adapters.
        HomeFragment: Exibe a lista de filmes.
        DetailFragment: Exibe os detalhes do filme.
        Adapters: Configuração dos adaptadores para o RecyclerView.
        ViewModel: Conecta os dados à UI, atualizando a tela com base nos estados.

Estrutura do Projeto

O projeto é dividido em módulos para separar as responsabilidades de forma clara

Dependências Externas

Este projeto utiliza várias bibliotecas externas para facilitar o desenvolvimento e melhorar a performance da aplicação. Algumas das principais dependências incluem:

    Dagger Hilt: Para injeção de dependência.
    Retrofit: Para realizar chamadas HTTP.
    Interceptor: Para interceptar requisições e respostas HTTP.
    Coroutines: Para programação assíncrona com coroutines.
    Glide: Para carregar e exibir imagens (ex: posters de filmes).
    Shimmer: Para animação de carregamento.
    Timber: Para logging.
    Mockito Kotlin: Para mocks em testes unitários.
    JUnit: Framework para testes unitários.
    Truth: Biblioteca para asserções em testes.
    Testing LiveData: Para testar o comportamento do LiveData nas ViewModels.

Funcionalidades
Tela Inicial:

    Exibe uma lista de filmes em um RecyclerView no formato de grid com 3 colunas.
    Enquanto os filmes estão sendo carregados, a tela mostra um efeito de Shimmer.
    A Toolbar contém uma função de pesquisa dinâmica (letra por letra).
    Um ícone de calendário permite filtrar filmes do mês atual.

Tela de Detalhes do Filme:

    Ao clicar em um filme na lista, o usuário é redirecionado para a tela de detalhes com informações como:
        Poster do filme.
        Sinopse.
        Gêneros.
        Elenco.
        Duração.
        Data de lançamento (se disponível).
        Botão de compartilhar a URL do filme.

Funcionalidade de Busca e Filtro:

    Busca por nome de filme: A pesquisa é feita letra por letra. Pode ter um pequeno atraso, especialmente em emuladores.
    Filtro de filmes em pré-venda: Filmes em pré-venda são destacados com cor laranja.

Como Executar
Pré-requisitos

    Android Studio (recomendado versão mais recente)
    JDK 8 ou superior
    Emulador Android ou dispositivo físico para testes

Passos para Executar

    Clone o repositório para sua máquina local:

    git clone https://github.com/HectorFortuna/desafio-mobile-android.git

    Abra o projeto no Android Studio.

    Execute o projeto clicando no ícone Run ou pressionando Shift + F10.

    O aplicativo será executado no emulador ou dispositivo conectado.

Testes

Este projeto inclui testes unitários utilizando o JUnit, Mockito Kotlin, e LiveData Testing.
Para rodar os testes:

    Abra o Android Studio.

    Vá até Run > Run 'Tests' ou use o comando:

    ./gradlew test


 Hector Fortuna Suárez