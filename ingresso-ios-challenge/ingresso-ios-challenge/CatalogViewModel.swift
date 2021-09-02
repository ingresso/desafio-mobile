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
    @Published var items : [Item] = []
    @Published var catalog : Catalog = Catalog(items: [], count: 0)
    
    
    init() {
       // fetchCatalogs()
        getCatalogs()
    }
    
    func fetchCatalogs(){
        
        let urlRequest = CatalogEndpoint.getAll
        
        AF.request(urlRequest).validate().publishDecodable(type: Catalog.self, decoder: JSONDecoder())
            .compactMap{$0.value}
            .map{$0.items.self}
            .sink(receiveCompletion: {completion in
                switch completion{
                case .finished:
                    //LOG
                    ()
                case .failure(let failture):
                    print("deu erro")
                    //LOG
                    print(failture.localizedDescription)
                }
                
            }, receiveValue: { (receivedValue : [Item]) in
                
                self.items = receivedValue
                print(self.items[0])
            }).store(in: &subscription)
            
    
    }
    
    
    func getCatalogs() {
        AF.request(CatalogEndpoint.getAll).response { (responseData) in
            guard let data = responseData.data else {return}
            do{
                let catalogList = try JSONDecoder().decode(Catalog.self, from: data)
                self.catalog = catalogList
                //print(catalogList.items[0])
                //LOG Count registers
            } catch {
                print(error)//LOG
            }
            
        }
        
        
    }
    
    
}
    




    extension Data{
        func parseData(removeString string: String) -> Data? {
            let dataAsString = String(data: self, encoding: .utf8)
            let parseDataAsString = dataAsString?.replacingOccurrences(of: string, with: "")
            guard let data = parseDataAsString?.data(using: .utf8) else {
                return nil
            }
            return data
        }
    
    }
    


    
 

