//
//  ViewModel.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import Foundation
import Combine

final class ViewModel: ObservableObject {
    
    @Published var movies: [Movie] = []
    
    func fetchAllMovies(handler: @escaping () -> Void) {
        if let url = URL(string: "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio") {
            print(url)
            let session = URLSession(configuration: .default)
            let task = session.dataTask(with: url) { (data, response, error) in
                if error == nil {
                    let decoder = JSONDecoder()
                    if let moviesData = data {
                        do {
                            let movies = try decoder.decode(Movies.self, from: moviesData)
                            DispatchQueue.main.async {
                                self.movies = movies.items
                                return handler()
                            }
                        } catch {
                            print(error)
                        }
                    }
                }
                handler()
            }
            task.resume()
        }
    }
}
