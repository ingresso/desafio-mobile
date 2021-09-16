//
//  MovieInfoRow.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 14/09/21.
//

import SwiftUI

struct MovieInfoRow: View {
    
    var title: String
    var description: String
    var isContentRatingRow: Bool?
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title).font(.headline).fontWeight(.bold).padding(.bottom, 2)
            
            if let _ = isContentRatingRow {
                ContentRatingView(contentRating: description)
                    .padding(.bottom)
            }
            else {
                Text(description).font(.footnote).padding(.bottom)
            }
        }
    }
}
