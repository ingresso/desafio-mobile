//
//  EventService.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//

import Foundation

class EventService {
    
    private let urlString = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio"
    
    // Função que da um search nos filmes
    func fetchComingSoonEvents() async throws -> [Event] {
        // Validar a URL
        guard let url = URL(string: urlString) else {
            throw URLError(.badURL)
        }
        
        let data: Data
        let response: URLResponse
        
        // Fazer a requisição
        do {
            (data, response) = try await URLSession.shared.data(from: url)
            print("DEBUG: Dados recebidos, tamanho: \(data.count) bytes")
        } catch {
            print("DEBUG: Erro de rede detalhado: \(error)")
            throw error
        }
        
        // Verificação do servidor
        guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
            print("DEBUG: Resposta do servidor inválida")
            throw URLError(.badServerResponse)
        }
        
        // Decodificar o JSON
        do {
            let decodedResponse = try JSONDecoder().decode(EventResponse.self, from: data)
            return decodedResponse.items
        } catch {
            print("DEBUG: Erro ao decodificar JSON: \(error)")
            throw error
        }
    }
}
