//
//  APIConfiguration.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import Alamofire
import Foundation

protocol APIConfiguration :    URLRequestConvertible {
    var method : HTTPMethod { get }
    var parameters : Parameters? { get }
    
}

struct Constants {
    struct Server {
        static let getAllCatalogs = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio"
    }
}

enum HTTPMethods: String{
    case post = "POST"
    case put = "PUT"
    case delete = "DELETE"
    case get = "GET"
    
}

enum ContentType: String {
    case json = "application/json"
}

enum HTTPHeaderField: String {
    case authentication = "Authorization"
    case contentType = "Content-Type"
    case acceptType = "Accept"
    case acceptEncoding = "Accept-Encoding"
}
