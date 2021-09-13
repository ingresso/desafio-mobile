//
//  ViewModel.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import Foundation
import Combine

class ApiCall {
    func getMovies(completion:@escaping (Movies) -> ()) {
        guard let url = URL(string: "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio") else { return }
        URLSession.shared.dataTask(with: url) { (data, _, _) in
            let movies = try! JSONDecoder().decode(Movies.self, from: data!)
            print(movies)
            
            DispatchQueue.main.async {
                completion(movies)
            }
        }
        .resume()
    }
}
