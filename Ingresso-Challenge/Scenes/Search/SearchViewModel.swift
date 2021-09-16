//
//  SearchViewModel.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 14/09/21.
//

import Foundation
import Combine

final class SearchViewModel: ObservableObject {
    @Published var searchText: String = ""
    var movies: [Movie]
    
    init(movies: [Movie]) {
        self.movies = movies
    }
    
    func filterMovies() -> [Movie] {
        return movies.filter({ $0.title.lowercased().contains(searchText.lowercased()) })
    }
}
