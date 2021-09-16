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
    @Published var isLoading: Bool = false
    
    init() {
        self.dataSource = APIDataSource()
    }
    
    func getMovies() {
        self.isLoading = true
        dataSource.getMovies { result in
            DispatchQueue.main.async {
                self.isLoading = false
            }
            
            switch result {
            case .success(let apiResponse):
                var response = apiResponse.items.map({ Movie(from: $0) })
                
                // Format date to display format
                let dateFormatter = DateFormatter()
                dateFormatter.dateFormat = "dd/MM/YY"
                
                response.sort(by: { dateFormatter.date(from: $0.premiereDate ?? "01/01/1900") ?? Date() < dateFormatter.date(from: $1.premiereDate ?? "01/01/1900") ?? Date()})
                
                DispatchQueue.main.async {
                    self.movies = response
                }
            case .failure(_):
                DispatchQueue.main.async {
                    self.showErrorAlert = true
                }
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
