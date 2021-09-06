//
//  MoviesViewModel.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

final class MoviesViewModel: ObservableObject {
    private let dataSource: DataSource
    
    @Published var movies: [Movie] = []
    @Published var showErrorAlert: Bool = false
    @Published var showSearchView: Bool = false
    
    init() {
        self.dataSource = APIDataSource()
    }
    
    func getMovies() {
        dataSource.getMovies { result in
            switch result {
            case .success(let apiResponse):
                DispatchQueue.main.async {
                    self.movies = apiResponse.items.map({ Movie(from: $0) })
                }
            case .failure(_):
                self.showErrorAlert = true
            }
        }
    }
    
    /// Creates the Movie Detail ViewModel to be passed to see movie details
    /// - Parameter movie: The selected movie
    /// - Returns: Movie Detail ViewModel
    func didTapMovie(movie: Movie) -> MovieDetailViewModel {
        return MovieDetailViewModel(movie: movie)
    }
}
