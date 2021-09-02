//
//  CatalogViewModel.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import Foundation
import Combine
import Alamofire

class CatalogViewModel: ObservableObject, Identifiable {
    
    private var subscription = Set<AnyCancellable>()
    @Published var catalogs : [Item] = []
    
    init() {
        fetchCatalogs()
        
    }
    
    func fetchCatalogs(){
        
        let urlRequest = CatalogEndpoint.getAll
        
        AF.request(urlRequest).validate().publishDecodable(type: Catalog.self, decoder: JSONDecoder())
            .compactMap{$0.value}
            .map{$0.items.self}
            .sink(receiveCompletion: {completion in
                switch completion{
                case .finished:
                    ()
                case .failure(let failture):
                    print("deu erro")
                    print(failture.localizedDescription)
                }
                
            }, receiveValue: { (receivedValue : [Item]) in
                self.catalogs = receivedValue
                print(self.catalogs[0])
            }).store(in: &subscription)
            
    
    }
    
    
}
