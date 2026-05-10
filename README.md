#  Desafio Mobile - Ingresso.com

1 - Tecnologias e Frameworks

Swift / SwiftUI: Interface declarativa e moderna.

MVVM (Model-View-ViewModel): Arquitetura estruturada para separação clara de responsabilidades.

Async/Await: Gerenciamento moderno de concorrência e chamadas de rede.

Kingfisher: Biblioteca robusta para download e cache de imagens.

XCTest: Testes unitários para validação de regras de negócio.

2 - Arquitetura Offline-First & Persistência de Dados

O aplicativo foi construído para ser resiliente e funcionar de forma fluida mesmo em cenários de conectividade instável ou nula (Modo Avião).

Fallback Offline (JSON): Interceptação do sucesso da requisição da API para salvar os dados brutos (Data) localmente via UserDefaults. Em caso de falha de rede, o app decodifica os dados locais, garantindo que listas e detalhes operem 100% offline.

Cache em Duas Camadas (Kingfisher): Imagens cacheadas automaticamente em memória (RAM) para transições instantâneas e em disco (Storage) para uso offline contínuo, substituindo limitações do framework nativo.

Image Prefetching Silencioso: Utilização do ImagePrefetcher para baixar todos os pôsteres em background assim que o JSON é recebido, evitando carregamentos pendentes (lazy loading) caso o usuário perca a conexão durante a navegação.

Persistência de Estado do Usuário: Fluxo de perfil sem fricção. Os dados de sessão (Login/Logout) e a lista de Favoritos (Set de IDs) são preservados no UserDefaults, garantindo que a curadoria do usuário sobreviva ao encerramento completo do app.

Sincronização de Logout: Implementação de uma lógica de segurança que limpa automaticamente a lista de favoritos local e do disco quando o usuário realiza o logout, garantindo a privacidade dos dados.

3 - Desafios Técnicos & Soluções Estruturais

Gestão Segura de Concorrência: Implementação de locks de estado no ViewModel para prevenir chamadas simultâneas à rede e tratamento amigável de interrupções de renderização do sistema (URLError.cancelled), mantendo o ciclo de vida da UI estável.

Tratamento de Dados Inconsistentes: Identificação de falhas no payload da API (ex: strings vazias em imagens ou campos nulos). O app reage exibindo placeholders com suporte a Glassmorphism e fallbacks de texto (ex: "- min" para durações nulas), evitando quebras de layout.

Sincronização de Estado Reativa: Utilização de Set<String> no ViewModel (injetado via @ObservedObject) para gerenciamento global. Favoritar um filme na tela de Detalhes reflete instantaneamente na Home, sem necessidade de callbacks complexos.

Aritmética de Datas Nativa: Refatoração da lógica de "Últimas Estreias" vs "Em Breve". O app realiza a extração do prefixo ISO (yyyy-MM-dd) e compara estritamente com a data do dispositivo local, ignorando falsos positivos da flag genérica inPreSale do servidor.

4 - UI/UX Imersiva e Funcionalidades Core

Carrossel:

Foco e Profundidade: O filme em destaque recebe escala (focusScale) e sombra profunda. Uso de desfoque progressivo (.blur) nos cards adjacentes para simular profundidade de campo de uma lente fotográfica.

Física de Molas e Haptics: Navegação por gestos usando .interactiveSpring e feedback tátil (UISelectionFeedbackGenerator), simulando peso físico na troca de cards.

Aritmética Modular Infinita: Lógica de loop contínuo para meses com alto volume de filmes, alternando automaticamente para rolagem finita em listas curtas (como a aba Favoritos).

Search UX Imersiva:

Animação Dinâmica: A barra de busca "sequestra" a Navigation Bar. Ícones realizam fade out enquanto o campo de texto assume o centro da tela.

Gestão de Foco Inteligente: Integração com @FocusState, acionando o teclado instantaneamente no primeiro toque na lupa.

Inteligência de Contexto & Localização:

Verificação assíncrona (MKReverseGeocodingRequest) para converter coordenadas em cidades sem travar a Main Thread.

Match dinâmico entre a localização do usuário e os metadados da API para exibir o badge de "Ingressos disponíveis na sua localização". Caso a permissão do iOS seja revogada, a memória de localização é limpa imediatamente por segurança.

Apple Guidelines & Liquid Glass:

Implementação nativa do .glassEffect (materials translúcidos) para botões flutuantes e modais, permitindo que a arte visual dos pôsteres domine o background escuro da aplicação.

5 - Integrações Nativas e Qualidade

Deep Links e Compartilhamento:

ShareLink Nativo: O sistema detecta a presença da URL da API. Se existente, gera um Rich Preview; caso contrário, compartilha um fallback em texto formatado com a sinopse do filme.

OpenURL: Botões de "Trailer" e "Ingressos" integrados ao ambiente do iOS para transições suaves para o YouTube ou Safari.

Cobertura de Testes (XCTest):

Validação das regras de ordenação cronológica do EventViewModel, garantindo que algoritmos de exibição respeitem a ordem crescente de datas de estreia e não quebrem com payloads de meses futuros.
