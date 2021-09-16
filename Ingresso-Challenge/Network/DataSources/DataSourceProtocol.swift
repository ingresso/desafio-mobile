//
//  DataSourceProtocol.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

protocol DataSource {
    func getMovies(completion: @escaping (Result<APIResponse, Error>) -> Void)
}
