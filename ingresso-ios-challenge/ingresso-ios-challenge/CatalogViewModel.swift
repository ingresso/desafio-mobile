//
//  CatalogViewModel.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import Foundation
import Combine
import Alamofire

class CatalogViewModel: ObservableObject {
    
    private var subscription = Set<AnyCancellable>()
    @Published var catalogItemsAvailableSoon = [Item]()
    
    init() {
        fetchCatalogs()
    }
    
    
    func fetchCatalogs(){
        
        let decoderStrategy = JSONDecoder()
        decoderStrategy.dateDecodingStrategy = .iso8601
        
        let urlRequest = CatalogEndpoint.getAll
        
        AF.request(urlRequest).validate().publishDecodable(type: Catalog.self, decoder: decoderStrategy)
            .compactMap{$0.value}
            .map{$0.items.self}
            .sink(receiveCompletion: {completion in
                switch completion{
                case .finished:
                    //LOG count registers
                    ()
                case .failure(let failture):
                    //LOG
                    print(failture.localizedDescription)
                }
                
            }, receiveValue: { (receivedValue : [Item]) in
                
                
                var datedFilmes = receivedValue.filter { $0.premiereDate != nil }
                datedFilmes = datedFilmes.sorted(by: {$0.premiereDate?.localDate?.compare(($1.premiereDate?.localDate)!) == .orderedAscending})
                
                self.catalogItemsAvailableSoon = datedFilmes
                
                
            }).store(in: &subscription)
        
    }
    
}







