//
//  MovieCell.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import SwiftUI
import URLImage

struct MovieCell: View {
    
    var movie: Movie
    
    init(_ movie: Movie) {
        self.movie = movie
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack {
                if let imageString = movie.imageURL, let imageURL = URL(string: imageString) {
                    // Async image loading, display placeholder while fetching data
                    URLImage(imageURL) {
                        Image("placeholder").resizable().aspectRatio(contentMode: .fit)
                    } inProgress: { _ in
                        Image("placeholder").resizable().aspectRatio(contentMode: .fit)
                    } failure: { _, _ in
                        Image("placeholder").resizable().aspectRatio(contentMode: .fit)
                    } content: { image in
                        image.resizable().aspectRatio(contentMode: .fit)
                    }

                }
                else {
                    Image("placeholder")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                }
                
                VStack {
//                    Spacer()
                    Text("09/09/21")
                        .fontWeight(.medium)
                        .foregroundColor(.white)
                }
            }
            .background(Color(.imageBackgroundGray))
            
            Text(movie.title)
                .font(.footnote)
                .lineLimit(2)
            
            Spacer()
        }
    }
}
