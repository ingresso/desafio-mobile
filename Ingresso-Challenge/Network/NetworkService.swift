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
    private enum NetworkError: LocalizedError {
        case urlError
        case notFound
        case badRequest
        case serverError
        case noDataError
        case unknownError
        
        var errorDescription: String {
            switch self {
            case .urlError:
                return "Can not create URL object from provided string"
            case .notFound:
                return "Not Found"
            case .badRequest:
                return "Bad request"
            case .serverError:
                return "Internal Server Error"
            case .noDataError:
                return "No data send by the server"
            case .unknownError:
                return "Something went wrong."
            }
        }
    }
    
    private let urlSession: URLSession = URLSession(configuration: .default)
    
    func request(urlString: String, completion: @escaping (Result<Data, Error>) -> Void) {
        guard let url = URL(string: urlString) else {
            completion(.failure(NetworkError.urlError))
            return
        }
        
        urlSession.dataTask(with: url) { data, response, error in
            if let error = error {
                completion(.failure(error))
                return
            }
            
            if let response = response as? HTTPURLResponse, let error = self.handle(statusCode: response.statusCode) {
                completion(.failure(error))
            }
            
            if let data = data {
                completion(.success(data))
            }
            else {
                completion(.failure(NetworkError.noDataError))
            }
        }.resume()
    }
    
    private func handle(statusCode: Int) -> NetworkError? {
        switch statusCode {
        case 200...299:
            return nil
        case 300...399:
            return nil
        case 400...499:
            return .badRequest
        case 404:
            return .notFound
        case 500...599:
            return .serverError
        default:
            return .unknownError
        }
    }
}
