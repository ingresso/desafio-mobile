//
//  MovieItem.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

import SwiftUI
import SDWebImageSwiftUI

struct MovieItem: View {
    
    var movie: Movie
    
    var body: some View {
        
        VStack() {
            
            if ContentView.🌎🎵 {
                Image("universal_poster")
                    .renderingMode(.original)
                    .resizable()
                    .frame(width: 120, height: 150, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                    .scaledToFit()
                    .padding(.top)
            }
            else {
                if movie.images.count > 0 {
                    WebImage(url: URL(string: movie.images[0].url))
                        .placeholder(
                            Image(systemName: "exclamationmark.triangle"))
                        .renderingMode(.original)
                        .resizable()
                        .frame(width: 120, height: 150, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                        .scaledToFit()
                        .padding(.top)
                }
                else {
                    
                    //Imagem para quando um filme não tem cartaz
                    
                    ZStack {
                        Rectangle()
                            .foregroundColor(.gray)
                            .frame(width: 120, height: 150, alignment: .center)
                            .scaledToFit()
                            .padding(.top)
                        
                        Image(systemName: "film")
                            .scaleEffect(2)
                    }
                }
            }
            
            ZStack {
                Rectangle()
                    .strokeBorder(Color.white,lineWidth: 2)
                    .background(Rectangle().foregroundColor(.black).opacity(0.5))
                    .frame(width: 110, height: 25, alignment: .center)
                    .offset(y: -35)
                    .opacity(movie.premiereDate != nil ? 1 : 0)
                    
                Text("\(movie.premiereDate?.dayAndMonth ?? "Sem")/\(movie.premiereDate?.year ?? "data")")
                    .bold()
                    .offset(y: -35)
                    .foregroundColor(.white)
                    .opacity(movie.premiereDate != nil ? 1 : 0)
            }
            
            Text(movie.title)
                .bold()
                .padding(.top, -30)
                .padding(.horizontal)
                .foregroundColor(.white)
        }
        .frame(width: 150, height: 230, alignment: .top)
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color(.gray)
                .frame(width: 150, height: 230, alignment: .top)
            MovieItem(movie:
                        Movie(
                            id: "Aaaaa",
                            title: "O Selênio Dos Incandescentes",
                            images: []
                                /*[ImageInfo(
                                    url: "https://ingresso-a.akamaihd.net/prd/img/movie/respect-a-historia-de-aretha-franklin/ea2b1022-a4ce-4cdc-956a-3e2088553c36.jpg",
                                    type: ImageType.posterPortrait)]*/,
                            premiereDate: PremiereDate(
                                dayAndMonth: "09/09", year: "2022",localDate: "bruh")
                        )
            )
            .previewLayout(.fixed(width: 150, height: 230))
        }
    }
}
