//
//  ContentRatingView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import SwiftUI

struct ContentRatingView: View {
    
    let movie: Movie
    
    struct MovieLayout {
        let color: Color
        let text: String
    }
    
    let movieMap: [String: MovieLayout] =
        [
            "Livre": MovieLayout(color: Color(.freeRatingColor), text: "L"),
            "10 anos": MovieLayout(color: .blue, text: "10"),
            "12 anos": MovieLayout(color: Color(.twelveRatingColor), text: "12"),
            "14 anos": MovieLayout(color: Color(.fourteenRatingColor), text: "14"),
            "16 anos": MovieLayout(color: Color(.sixteenRatingColor), text: "16"),
            "18 anos": MovieLayout(color: Color(.eighteenRatingColor), text: "18"),
            "Verifique a Classificação": MovieLayout(color: Color(.unknownRatingColor), text: "?"),
        ]
    
    var body: some View {
        Text(movieMap[movie.contentRating]?.text ?? "?")
            .font(.system(size: 12))
            .fontWeight(.heavy)
            .frame(width: 24.0, height: 24.0)
            .background(movieMap[movie.contentRating]?.color ?? Color(.primaryGray))
    }
}
