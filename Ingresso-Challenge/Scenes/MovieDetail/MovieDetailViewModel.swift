//
//  MovieDetailViewModel.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import Foundation
import Combine

final class MovieDetailViewModel: ObservableObject {
    let movie: Movie
    
    init(movie: Movie) {
        self.movie = movie
    }
}
