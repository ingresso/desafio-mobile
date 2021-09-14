//
//  ComingSoonMovies.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

struct ComingSoonMovies: View {
    
    @EnvironmentObject var viewModel: ViewModel
    
    private var columns = [GridItem(.flexible()),
                                 GridItem(.flexible())]
    
    var body: some View {
        ZStack {
            Color(.darkGray)
                .ignoresSafeArea()
            
            ScrollView {
                LazyVGrid(columns: columns, spacing: 0) {
                    ForEach(viewModel.organizedMovies, id: \.self) { item in
                        MovieItem(movie: item)
                    }
                }
                .padding(.horizontal)
            }
        }
    }
    
}

struct ComingSoonMovies_Previews: PreviewProvider {
    static var previews: some View {
        ComingSoonMovies()
            .environmentObject(ViewModel())
    }
}
