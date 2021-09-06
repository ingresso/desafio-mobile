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
                    .background(LinearGradient(gradient: Gradient(colors: [.clear, Color(.primaryGray)]), startPoint: .top, endPoint: .bottom))
                }
                
            }
        }.background(Color(.primaryGray).edgesIgnoringSafeArea(.all))
    }
}
