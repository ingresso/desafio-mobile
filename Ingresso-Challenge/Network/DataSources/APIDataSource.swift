//
//  APIDataSource.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

final class APIDataSource: DataSource {
    
    // MARK:- Properties
    private let baseURL: String = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio"
    
    func getMovies(completion: @escaping (Result<APIResponse, Error>) -> Void) {
        NetworkService.shared.request(urlString: baseURL) { result in
            switch result {
            case .success(let data):
                do {
                    let response = try JSONDecoder().decode(APIResponse.self, from: data)
                    completion(.success(response))
                }
                catch {
                    completion(.failure(error))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
}
