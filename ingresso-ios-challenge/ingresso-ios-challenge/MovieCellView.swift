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
    let defaultImageUrl = """
                          https://play-lh.googleusercontent.com/aAh_erLSqnztKNtJg6VFKkT1JkgIC1Vx9Mp2K2qg5ahtFUanQxj8G0m1raNKji5cDW8
                          """
    
    var body: some View {
        
        VStack{
            HStack{
                ZStack{
                    KFImage(URL(string: currentItem.images.first?.url ?? defaultImageUrl))
                        .resizable()
                        .animation(.easeIn)
                        .frame(width: 110, height: 150)
                        .scaledToFill()
                        .border(Color.black)
                        .shadow(color: .white, radius: 6, x: 0.0, y: 0.0)
                        .opacity(0.8)
                        
                    
                    VStack{
                        Spacer()
                        (((currentItem.premiereDate?.localDateFormated) != nil) ? Text((currentItem.premiereDate?.localDateFormated)!)
                            .foregroundColor(.white)
                            .font(.system(size: 25))
                            :
                            Text(""))
                            .border(Color.white, width: 1)
                        
                    }
                    Spacer()
                }
            }
            Text(currentItem.title)
                .foregroundColor(Color.white)
                
            
        }
        
    }
}

struct MovieCellView_Previews: PreviewProvider {
    static var previews: some View {
        MovieCellView(currentItem: Item(id: "", title: "", originalTitle: "", movieIdUrl: "", ancineId: "", countryOrigin: "", priority: 0, contentRating: "", duration: "", rating: 0, synopsis: "", cast: "", director: "", distributor: "", inPreSale: false, isReexhibition: false, urlKey: "", isPlaying: false, countIsPlaying: 0, premiereDate: nil, creationDate: "", city: "", siteUrl: "", nationalSiteUrl: "", images: [], genres: [], trailers: [], rottenTomatoe: nil)).previewDevice("iPhone 12")
    }
}
