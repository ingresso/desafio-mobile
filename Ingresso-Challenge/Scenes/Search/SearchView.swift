//
//  SearchView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 05/09/21.
//

import SwiftUI
import URLImage

struct SearchView: View {
    
    var movies: [Movie]
    @State var searchText: String = ""
    @Binding var showSearchView: Bool
    
    var body: some View {
        NavigationView {
            ZStack{
                Color.black.opacity(0.5).edgesIgnoringSafeArea(.all)
                VStack {
                    SearchBar(searchText: $searchText, showSearchView: $showSearchView)
                        .padding(.horizontal)
                    
                    // Search results list
                    ScrollView(.vertical, showsIndicators: false) {
                        ForEach(movies.filter({ $0.title.lowercased().contains(searchText.lowercased()) })) { movie in
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
            .background(BackgroundBlurView().edgesIgnoringSafeArea(.all))
            .navigationBarHidden(true)
        }.background(BackgroundBlurView())
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView(movies: [], showSearchView: .constant(true))
    }
}
