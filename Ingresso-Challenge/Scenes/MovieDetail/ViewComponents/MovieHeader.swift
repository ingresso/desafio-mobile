//
//  MovieHeader.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 14/09/21.
//

import SwiftUI

struct MovieHeader: View {
    
    var imageURL: String?
    var title: String
    var genres: [String]
    
    var body: some View {
        HStack(alignment: .bottom) {
            AsyncImage(imageString: imageURL)
                .frame(height: 200)
                .shadow(color: .black, radius: 2, x: -2, y: 2)
            
            VStack(alignment: .leading, spacing: 8) {
                Text(title)
                    .fontWeight(.heavy)
                
                Text(genres.first ?? "" ).font(.footnote)
            }
            
            Spacer()
        }
        .padding()
        .background(LinearGradient(gradient: Gradient(colors: [.clear, Color(.darkerGray)]), startPoint: .top, endPoint: .bottom))
    }
}
