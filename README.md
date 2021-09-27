# Desafio Mobile

## Funcionalidades
* Loading.
* Lista de filmes que entrarão em cartaz.
* Tela de detalhe de um filme.
* Compartilhamento.
* Opção de favoritar.
* Busca.

## Estrutura do projeto
O projeto ultilizou o padrão MVVM no que resultou a sequinte estrutura:
* data - Esta camada é divida em local, onde se encontra o modelo de database local e remote onde se localiza a chamada de Api e seus modelos de resposta.
* di - Camada responsável para realizar a injeção de dependência.
* repositores - Camada cuja função é media a ViewModel e os dados da api.
* ui - Onde se encontra todas ViewModels e classes para interface do usuário.
* utils - Esta camada contém classes que auxiliam no funcionamento do projeto de forma mais simplificada.  

## Execução do projeto
Clonar o repositório e importar no Android Studio.

## Requisitos 
* Gradle JDK 11.0.12
* minSdkVersion 21
* targetSdk 31

## Linguagem e Tecnologias utilizadas
* Kotlin
* Padrão de arquitetura MVVM
* LiveData
* Retrofit
* Gson
* Coroutines
* Glide
* View Binding
* Room Database
* Dagger Hilt
