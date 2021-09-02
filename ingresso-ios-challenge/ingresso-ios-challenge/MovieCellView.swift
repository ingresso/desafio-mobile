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
    let defaultImageUrl = "https://media.istockphoto.com/vectors/creative-vector-illustration-of-old-retro-film-strip-frame-set-on-vector-id1008655852"
    
    var body: some View {
        ZStack{
            VStack{
                Spacer()
                HStack{
        
                    Text("data")
                        .foregroundColor(.white)
                        .padding(.bottom, 4)
                }
            }
            
        }.background(KFImage(URL(string: currentItem.images.first?.url ?? defaultImageUrl)))
        .cornerRadius(3.0)
        .frame(width: 130, height: 180)
        .shadow(color: .white, radius: 6, x: 0.0, y: 0.0)
        .scaledToFit()
        .border(Color.black, width: 1)     
        
        
    }
}

struct MovieCellView_Previews: PreviewProvider {
    static var previews: some View {
        MovieCellView(currentItem: Item(id: "", title: "", originalTitle: "", movieIdUrl: "", ancineId: "", countryOrigin: "", priority: 0, contentRating: "", duration: "", rating: 0, synopsis: "", cast: "", director: "", distributor: "", inPreSale: false, isReexhibition: false, urlKey: "", isPlaying: false, countIsPlaying: 0, premiereDate: nil, creationDate: "", city: "", siteUrl: "", nationalSiteUrl: "", images: [], genres: [], trailers: [], rottenTomatoe: nil)).previewDevice("iPhone 12")
    }
}
