//
//  MovieDetailViewModel.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import Foundation
import Combine
import UIKit

final class MovieDetailViewModel: ObservableObject {
    let movie: Movie
    
    init(movie: Movie) {
        self.movie = movie
    }
    
    func shareMovie() {
        guard let url = URL(string: movie.siteURL) else { return }
        
        let activityController = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController!.present(activityController, animated: true, completion: nil)
    }
}
