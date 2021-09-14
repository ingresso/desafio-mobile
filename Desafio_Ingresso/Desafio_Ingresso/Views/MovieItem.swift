//
//  MovieItem.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

import SwiftUI

struct MovieItem: View {
    
    //var movie: Movie
    
    var body: some View {
        
        VStack {
            
            Image("test_poster")
                .renderingMode(.original)
                .resizable()
                .frame(width: 120, height: 150, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .scaledToFit()
                .padding(.top)
            
            Rectangle()
                .strokeBorder(Color.white,lineWidth: 2)
                .background(Circle().foregroundColor(.clear))
                .frame(width: 110, height: 25, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .offset(y: -35)
            
            Text("09/11/2021")
                .bold()
                .offset(y: -65)
                .foregroundColor(.white)
            
            Text("Movie Title ohhhh yeaaahhhh")
                .bold()
                .padding(.top, -60)
                .padding(.horizontal)
        }
        .frame(width: 150, height: 230, alignment: .top)
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        
        MovieItem()
        .previewLayout(.fixed(width: 150, height: 230))
        //MovieItem(movie: Movie(id: "123", title: "título do filme", images: [], premiereDate: PremiereDate(dayAndMonth: "02/10", year: "2022")))
    }
}
