//
//  ContentView.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

struct ContentView: View {
    
    @State private var isLoading = false
    @ObservedObject var viewModel = ViewModel()
    
    var body: some View {
        
        ZStack {
            
            ComingSoonMovies()
            
            if isLoading {
                Loading()
            }
        }
        .onAppear {
            isLoading = true
            viewModel.fetchAllMovies() {
                print(viewModel.movies)
                isLoading = false
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
