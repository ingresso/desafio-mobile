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
                    HStack(alignment: .bottom) {
                        AsyncImage(imageString: viewModel.movie.imagesURL.portrait)
                            .frame(height: 200)
                        
                        VStack(alignment: .leading, spacing: 8) {
                            Text(viewModel.movie.title)
                                .fontWeight(.heavy)
                            
                            Text(viewModel.movie.genres.first ?? "" ).font(.footnote)
                        }
                        
                        Spacer()
                    }
                    .padding()
                    .background(LinearGradient(gradient: Gradient(colors: [.clear, Color(.darkerGray)]), startPoint: .top, endPoint: .bottom))
                }
                
                //
                VStack(alignment: .leading) {
                    Group {
                        Text("Detalhes").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        ContentRatingView(movie: viewModel.movie)
                            .padding(.bottom)
                        
                        Text("Duração").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text("\(viewModel.movie.duration) min").font(.footnote).padding(.bottom)
                        
                        Text("Nome Original").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text(viewModel.movie.originalTitle).font(.footnote).padding(.bottom)
                        
                        Text("Elenco").fontWeight(.heavy).padding(.bottom, 2)
                        Text(viewModel.movie.cast).font(.footnote).padding(.bottom)
                    }
                    
                    Group {
                        Text("Sinopse").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text(viewModel.movie.synopsis).font(.footnote).padding(.bottom)
                        
                        Text("Diretor").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text(viewModel.movie.director).font(.footnote).padding(.bottom)
                        
                        Text("Distribuidor").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text(viewModel.movie.distributor).font(.footnote).padding(.bottom)
                        
                        Text("Pais de Origem").font(.headline).fontWeight(.bold).padding(.bottom, 2)
                        Text(viewModel.movie.countryOrigin).font(.footnote).padding(.bottom)
                    }
                }
                .padding()
                .frame(width: UIScreen.main.bounds.width - 16)
                .background(Color(.darkerGray))
                .padding(.top, 16)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .background(Color(.primaryGray).edgesIgnoringSafeArea(.all))
    }
}
