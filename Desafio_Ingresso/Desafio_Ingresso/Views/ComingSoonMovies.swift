//
//  ComingSoonMovies.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

struct ComingSoonMovies: View {
    
    @State private var isLoading = false
    
    @State var parsedMovies = Movies(items: [])
    @State var movieArray = [Movie]()
    
    var body: some View {
        Text("Welcome")
        .onAppear {
            ApiCall().getMovies { (movies) in
                parsedMovies = movies
                self.movieArray = parsedMovies.items
            }
        }
        ProgressView()
            .progressViewStyle(CircularProgressViewStyle(tint: .orange))
    }
}

struct ComingSoonMovies_Previews: PreviewProvider {
    static var previews: some View {
        ComingSoonMovies()
    }
}