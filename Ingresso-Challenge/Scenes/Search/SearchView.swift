//
//  SearchView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 05/09/21.
//

import SwiftUI
import URLImage

struct SearchView: View {
    
    @ObservedObject var viewModel: SearchViewModel
    @Binding var isShowing: Bool
    
    var body: some View {
        NavigationView {
            ZStack{
                Color.black.opacity(0.5).edgesIgnoringSafeArea(.all)
                VStack {
                    SearchBar(searchText: $viewModel.searchText, showSearchView: $isShowing)
                        .padding(.horizontal)
                    
                    if viewModel.filterMovies().isEmpty && viewModel.searchText != "" {
                        Spacer()
                        Image("confused-face")
                            .renderingMode(.template)
                            .resizable()
                            .frame(width: 50, height: 50)
                            .foregroundColor(.secondary)
                        
                        Text("Não encontramos o que você está procurando. Confira se pesquisou pelo nome certo, ou tente buscar outra palavra.")
                            .multilineTextAlignment(.center)
                            .foregroundColor(.secondary)
                            .padding(.horizontal, 16)
                        Spacer()
                        Spacer()
                    }
                    else {
                        // Search results list
                        ScrollView(.vertical, showsIndicators: false) {
                            ForEach(viewModel.filterMovies()) { movie in
                                NavigationLink(destination: MovieDetailView(viewModel: MovieDetailViewModel(movie: movie))){
                                    SearchListCell(movie: movie)
                                }
                                .buttonStyle(PlainButtonStyle())
                                .listRowBackground(Color.clear)
                                .frame(height: 80)
                                .padding(.horizontal)
                                .padding(.bottom, 4)
                            }
                        }
                    }
                }
            }
            .background(BackgroundBlurView().edgesIgnoringSafeArea(.all))
            .navigationBarHidden(true)
        }.background(BackgroundBlurView())
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView(viewModel: SearchViewModel(movies: []), isShowing: .constant(true))
    }
}
