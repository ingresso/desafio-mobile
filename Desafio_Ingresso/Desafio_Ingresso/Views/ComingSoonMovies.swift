//
//  ComingSoonMovies.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

struct ComingSoonMovies: View {
    
    @State private var isLoading = false
    
    @State var parsedMovies = [Movie]()
    
    var body: some View {
        ZStack {
            Text("Welcome")
            .onAppear {
                isLoading = true
                ApiCall().getMovies { (movies) in
                    parsedMovies = movies.items
                    isLoading = false
                }
            }
        }
    }
    
}

struct ComingSoonMovies_Previews: PreviewProvider {
    static var previews: some View {
        ComingSoonMovies()
    }
}
