//
//  ContentView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import SwiftUI
import URLImage
import URLImageStore

struct MoviesView: View {
    
    @ObservedObject var viewModel: MoviesViewModel
    
    init(viewModel: MoviesViewModel) {
        self.viewModel = viewModel
        self.viewModel.getMovies()
        
        // Config navigation bar title color
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor: UIColor.white]
    }
    
    // Config image cache
    let urlImageService = URLImageService(fileStore: URLImageFileStore(), inMemoryStore: URLImageInMemoryStore())
    
    let columns = [
        GridItem(.flexible(), spacing: 16),
        GridItem(.flexible(), spacing: 16),
        GridItem(.flexible(), spacing: 16),
    ]
    
    var body: some View {
        NavigationView {
            ScrollView(showsIndicators: false) {
                LazyVGrid(columns: columns, spacing: 16) {
                    ForEach(viewModel.movies) { movie in
                        MovieCell(movie)
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                    }
                }.padding(.horizontal, 4)
            }
            .navigationTitle("Filmes")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarItems(trailing: Button(action: {viewModel.showSearchView.toggle()}) {
                Image(systemName: "magnifyingglass")
                    .foregroundColor(Color(.primaryBlue))
                    .imageScale(.large)
            })
            .background(Color(.primaryGray).ignoresSafeArea())
        }
        .fullScreenCover(isPresented: $viewModel.showSearchView, content: {
            SearchView(movies: viewModel.movies, showSearchView: $viewModel.showSearchView)
            
        })
        .environment(\.urlImageService, urlImageService)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        MoviesView(viewModel: MoviesViewModel())
    }
}
