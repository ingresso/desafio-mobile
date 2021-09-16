//
//  MovieDetailView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import SwiftUI
import URLImage
import URLImageStore

struct MovieDetailView: View {
    
    @ObservedObject var viewModel: MovieDetailViewModel
    
    init(viewModel: MovieDetailViewModel) {
        self.viewModel = viewModel
    }
    
    let defaultHeaderHeight = UIScreen.main.bounds.height / 4
    
    var body: some View {
        ScrollView {
            VStack {
                
                ZStack(alignment: .bottomLeading) {
                    // Stetch image
                    GeometryReader { geometry in
                        AsyncImage(imageString: viewModel.movie.imagesURL.landscape)
                            .scaledToFill()
                            .frame(width: geometry.size.width, height: geometry.frame(in: .global).minY > 0 ? defaultHeaderHeight + geometry.frame(in: .global).minY: defaultHeaderHeight) // strech image based on user vertical scroll
                            .clipped()
                            .offset(y: geometry.frame(in: .global).minY > 0 ? -geometry.frame(in: .global).minY : 0) // sticky the image to the top
                        
                        LinearGradient(gradient: Gradient(colors: [.clear, .black]), startPoint: .bottom, endPoint: .top)
                            .offset(y: geometry.frame(in: .global).minY > 0 ? -geometry.frame(in: .global).minY : 0) // sticky the image to the top
                            .frame(height: geometry.frame(in: .global).minY > 0 ? defaultHeaderHeight + geometry.frame(in: .global).minY: defaultHeaderHeight) // strech image based on user vertical scroll
                            .opacity(0.8)
                    }
                    .frame(height: defaultHeaderHeight) // default size
                    
                    // Movie infos
                    MovieHeader(imageURL: viewModel.movie.imagesURL.portrait, title: viewModel.movie.title, genres: viewModel.movie.genres)
                }
                
                //
                VStack(alignment: .leading) {
                    MovieInfoRow(title: "Detalhes", description: viewModel.movie.contentRating, isContentRatingRow: true)
                    MovieInfoRow(title: "Duração", description: viewModel.movie.duration)
                    MovieInfoRow(title: "Nome Original", description: viewModel.movie.originalTitle)
                    MovieInfoRow(title: "Elenco", description: viewModel.movie.cast)
                    MovieInfoRow(title: "Sinopse", description: viewModel.movie.synopsis)
                    MovieInfoRow(title: "Diretor", description: viewModel.movie.director)
                    MovieInfoRow(title: "Distribuidor", description: viewModel.movie.distributor)
                    MovieInfoRow(title: "Pais de Origem", description: viewModel.movie.countryOrigin)
                }
                .padding()
                .frame(width: UIScreen.main.bounds.width - 16)
                .background(Color(.darkerGray))
                .padding(.top, 16)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .navigationBarItems(trailing: Button(action: {
            viewModel.shareMovie()
        }) {
            Image(systemName: "square.and.arrow.up")
                .foregroundColor(Color(.primaryBlue))
                .imageScale(.large)
        })
        .background(Color(.primaryGray).edgesIgnoringSafeArea(.all))
    }
}
