//
//  MovieCellView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI
import Kingfisher

struct MovieCellView: View {
//    let item : Item
//    let imageUrl = try item.images[0].url.asURL()
        

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
            
        }//.background(KFImage())
        .cornerRadius(3.0)
    }
}

struct MovieCellView_Previews: PreviewProvider {
    static var previews: some View {
        MovieCellView().previewDevice("iPhone 12")
    }
}
