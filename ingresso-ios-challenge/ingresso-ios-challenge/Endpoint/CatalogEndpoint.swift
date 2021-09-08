//
//  CatalogEndpoint.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import Alamofire
import Foundation


enum CatalogEndpoint : APIConfiguration {
    
    case getAll
    
    var method: HTTPMethod {
        switch self{
        case .getAll:
            return .get
        }
    }
    
    var parameters: Parameters?{
        switch self {
        case .getAll:
            return nil
        }
    }
    
    func asURLRequest() throws -> URLRequest {
        
        do{
            
            var urlRequest = URLRequest(url: try Constants.Server.getAllCatalogs.asURL())
            urlRequest.httpMethod = method.rawValue
            urlRequest.setValue(ContentType.json.rawValue, forHTTPHeaderField: HTTPHeaderField.contentType.rawValue)
            
            if let parameters = parameters {
                urlRequest.httpBody = try JSONSerialization.data(withJSONObject: parameters)
            }
            return urlRequest
        }
        
    }
    
}




