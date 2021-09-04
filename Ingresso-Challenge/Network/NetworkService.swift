//
//  NetworkService.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

/// Service responsible for calling the API
final class NetworkService {
    
    static let shared: NetworkService = NetworkService()
    
    // MARK:- Properties
    private let urlSession: URLSession = URLSession(configuration: .default)
    
    func request(urlString: String, completion: @escaping (Result<Data, Error>) -> Void) {
        guard let url = URL(string: urlString) else {
            // TODO: handle error
            return
        }
        
        urlSession.dataTask(with: url) { data, response, error in
            if let error = error {
                completion(.failure(error))
                return
            }
            
//            if let response = response {
//                 //TODO: Check response status code and send error via completion(.failure())
//                dump(response)
//            }
            
            
            if let data = data {
                completion(.success(data))
            }
            else {
                // TODO: Handle error
                //completion(.failure())
            }
        }.resume()
    }
}
