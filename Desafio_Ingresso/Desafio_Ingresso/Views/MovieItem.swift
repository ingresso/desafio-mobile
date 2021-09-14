//
//  MovieItem.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

import SwiftUI

struct MovieItem: View {
    
    var movie: Movie
    
    var body: some View {
        
        //Inicializar imagem com URL do filme aqui.
        
        VStack {
            
            Image("test_poster")
                .renderingMode(.original)
                .resizable()
                .frame(width: 120, height: 150, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .scaledToFit()
                .padding(.top)
            
            Rectangle()
                .strokeBorder(Color.white,lineWidth: 2)
                .background(Rectangle().foregroundColor(.black).opacity(0.5))
                .frame(width: 110, height: 25, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .offset(y: -35)
            
            Text("\(movie.premiereDate?.dayAndMonth ?? "Sem")/\(movie.premiereDate?.year ?? "data")")
                .bold()
                .offset(y: -65)
                .foregroundColor(.white)
            
            Text(movie.title)
                .bold()
                .padding(.top, -60)
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
                        Movie(id: "Aaaaa", title: "O Selênio Dos Incandescentes", images: [], premiereDate: PremiereDate(dayAndMonth: "09/09", year: "2022")))
            .previewLayout(.fixed(width: 150, height: 230))
        }
    }
}
