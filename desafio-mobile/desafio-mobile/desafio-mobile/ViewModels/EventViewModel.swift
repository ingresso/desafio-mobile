//
//  EventViewModel.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//

import Foundation
import Combine
import Kingfisher

@MainActor
class EventViewModel: ObservableObject {
    
    @Published var events: [Event] = []
    @Published var isLoading: Bool = false
    
    @Published var favoriteIDs: Set<String> = []
    private let offlineCacheKey = "offline_events_cache"
    private let favoritesKey = "user_favorites_ids"
    
    
    
    // Inicializador pra carregar os favoritos salvos assim que o ViewModel é criada
    init() {
        let savedIds = UserDefaults.standard.stringArray(forKey: favoritesKey) ?? []
        self.favoriteIDs = Set(savedIds)
    }
    
    // Função para carregar os dados
    func loadEvents() async {
        DispatchQueue.main.async { self.isLoading = true }
        
        do {
            // Tentando requisicao com internet
            
            let url = URL(string: "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio")!
            let (data, _) = try await URLSession.shared.data(from: url)
            
            // Decodificando pois antes estava pegando do array [Event]
            let decodedResponse = try JSONDecoder().decode(EventResponse.self, from: data)
            
            DispatchQueue.main.async {
                // Pegamos o array que está dentro do .items
                self.events = decodedResponse.items
                self.isLoading = false
            }
            
            // Salva os dados localmente
            UserDefaults.standard.set(data, forKey: offlineCacheKey)
            
            // Pré carregador pra carregar todas as imagens dos filmes assim que abrir
            prefetchImages(for: decodedResponse.items)
        } catch {
            print("Sem internet ou erro na API: \(error.localizedDescription)")
            
            // Requisição Offline, ou seja, pegando do package que já baixou as imagens
            if let cachedData = UserDefaults.standard.data(forKey: offlineCacheKey) {
                
                if let cachedResponse = try? JSONDecoder().decode(EventResponse.self, from: cachedData) {
                    DispatchQueue.main.async {
                        self.events = cachedResponse.items
                        self.isLoading = false
                        print("Filmes carregados do cache offline com sucesso!")
                    }
                    return
                }
            }
            
            DispatchQueue.main.async { self.isLoading = false }
        }
    }
    
    func toggleFavorite(event: Event) {
        if favoriteIDs.contains(event.id) {
            favoriteIDs.remove(event.id)
        } else {
            favoriteIDs.insert(event.id)
        }
        
        // Persistencia local com userDefaults, mas colocaria em CloudKit em grande escala
        UserDefaults.standard.set(Array(favoriteIDs), forKey: favoritesKey)
        
        objectWillChange.send()
    }
    
    func isFavorite(_ event: Event) -> Bool {
        favoriteIDs.contains(event.id)
    }
    // Limpando favoritos após Logout
    func clearFavorites() {
        self.favoriteIDs = []
        UserDefaults.standard.removeObject(forKey: favoritesKey)
        objectWillChange.send()
    }
    
    // Pré-carrega as imagens no cache silenciosamente
    private func prefetchImages(for events: [Event]) {
        let urls = events.compactMap { event -> URL? in
            guard let urlString = event.posterURL else { return nil }
            return URL(string: urlString)
        }
        
        // O Kingfisher baixa e guarda tudo no disco em segundo plano!
        ImagePrefetcher(urls: urls).start()
    }
    
}

extension String {
    func formatToMonthYear() -> String {
        let inputFormatter = DateFormatter()
        inputFormatter.dateFormat = "yyyy-MM"
        
        if let date = inputFormatter.date(from: self) {
            let outputFormatter = DateFormatter()
            outputFormatter.locale = Locale(identifier: "pt_BR")
            outputFormatter.dateFormat = "MMMM yyyy"
            return outputFormatter.string(from: date).capitalized
        }
        return "Em breve"
    }
}
