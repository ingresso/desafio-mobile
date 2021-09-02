//
//  MovieCellView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI
import Kingfisher

struct MovieCellView: View {
    let currentItem : Item
    
    var body: some View {
        ZStack{
            HStack{
                Text("Data de lançamento")
                    .overlay(
                        RoundedRectangle(cornerRadius: 20)
                            .fill(Color.red.opacity(0.5))
                    )
                    .foregroundColor(.white)
                
            }
            
        }.background(KFImage(URL(string: currentItem.images[0].url)))
        .cornerRadius(3.0)
        .shadow(color: /*@START_MENU_TOKEN@*/.black/*@END_MENU_TOKEN@*/, radius: 6, x: 0.0, y: 0.0)
    }
}

struct MovieCellView_Previews: PreviewProvider {
    static var previews: some View {
        MovieCellView(currentItem: Item(id: "", title: "", originalTitle: "", movieIdUrl: "", ancineId: "", countryOrigin: "", priority: 0, contentRating: "", duration: "", rating: 0, synopsis: "", cast: "", director: "", distributor: "", inPreSale: false, isReexhibition: false, urlKey: "", isPlaying: false, countIsPlaying: 0, creationDate: "", city: "", siteUrl: "", nationalSiteUrl: "", images: [], genres: [], trailers: [], rottenTomatoe: nil)).previewDevice("iPhone 12")
    }
}
