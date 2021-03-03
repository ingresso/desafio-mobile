# Desafio Mobile

O desafio consiste em criar um aplicativo que consuma uma API da Ingresso.com e apresente o resultado na tela.
A tela deve conter uma lista com os filmes que deverão entrar em cartaz em breve.



## Requisitos Técnicos

1. Linguagens: Swift ou Kotlin;
2. Utilizar uma IDE atualizada (latest stable version), XCode ou Android Studio;
3. Utilize uma ou mais bibliotecas de terceiro;
4. Faça pelo menos um teste unitário e/ou de interface;
5. Log é bom! Nós gostamos, eles precisam ser úteis;
6. Crie um Readme.md, organizado em tópicos, na raiz do projeto com instruções/comentários/explicações.

**No iOS utilize**
- Swift UI.

**No Android utilize**
- Constraint Layout.



## Requisitos do Produto

Como foi dito anteriormente, deve-se criar uma tela de filmes que ainda vão entrar em cartaz.
Alguns elementos precisam estar nessa interface como:

1. Tela de loading;
2. Poster (podem existir filmes ainda sem poster);
3. Nome do filme;
4. Data de estreia (se existir);
5. Ordene os filmes pelos quais estejam próximos de entrarem em cartaz (campo `premiereDate`);
6. Organize o projeto, preferêncialmente utilizando alguma arquitetura.

Você determina a interface, use a criatividade.
Nós fizemos essa tela assim.

<p align="center">
  <img src="filmes-em-breve-1.png" width="350" title="Tela de Filmes em breve, topo">
  <img src="filmes-em-breve-2.png" width="350" title="Tela de Filmes em breve, com data de estreia">
</p>

Observações:

1. Não é necessário colocar o elemento de publicidade;
2. A NavBar/Navigation Bar pode ser simples;
3. Você pode mudar totalmente essa tela;
4. Você pode implementar funcionalidades novas se desejar, abaixo existe uma lista com sugestões;
5. Animações são bem-vindas mas não obrigatórias.



## Sugestões para o Produto

Gostou do desafio? Gostaria de avançar mais?
As sugestões são:

1. Busca;
2. Pull to refresh;
3. Tela de detalhes de um filme;
4. Favoritar filme;
5. Compartilhar;
6. Filtrar por estreias do mês;
7. Alguns filmes podem estar em pré-venda (campo `isPreSale`), pode incluir um elemento diferenciado;
8. Animações são bem-vindas.



## API

Utilize a URL da nossa API que retorna um JSON com os filmes que irão entrar em cartaz.

**GET** [https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio](https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio)



## Como vamos receber o desafio?

**Opção 1 - Fork (preferêncial)**
- Faça um fork do desafio e desenvolva o seu projeto;
- Acabou de desenvolver? Submeta um pull request.
 
**Opção 2 - Repositório Privado**
- Faça o seu projeto em um repositório privado seu;
- Quando terminar vamos pedir para adicionar um avaliador como membro.

**Opção 3 - Plano C**
- Teve problema com as opções anteriores? Então compacte o seu projeto e envie para nós.



## Avaliação

**Código** 
- Legível e clean. 
- Pode usar comentários se achar necessário.

**Organização** 
- Separando em módulos/frameworks se possível;
- Está utilizando alguma arquitetura.

**Segurança** 
- Encontrou alguma vulnerabilidade? Viu algo que ficou desconfortável durante o desenvolvimento? Coloque no README. 
- Se for alguma falha grave, por favor nos acione por email.

**Documentação Básica**
- O README explica como rodar o projeto? 
- Explique a tomada de decisão. Escreva de forma organizada, em tópicos. 
- Imagine que você esteja escrevendo para você mesmo do futuro e não lembra de nada do projeto.

**Objetivo**
- O desafio está sendo feito o mínimo exigido.

**Commits** 
- Qualidade e padrão;
- Pode ser em inglês ou português.

**UX/UI**
- Interface amigável;
- Fácil de usar.

**One more thing**
- Tem mais alguma coisa que você queira nos contar?
