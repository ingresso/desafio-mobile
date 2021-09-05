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
            ZStack(alignment: .bottom) {
                if let imageString = movie.imagesURL.portrait, let imageURL = URL(string: imageString) {
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
                
                if let movieDate = movie.premiereDate {
                    Text(movieDate)
                        .italic()
                        .font(.system(size: 12))
                        .fontWeight(.heavy)
                        .foregroundColor(.white)
                        .padding(.vertical, 4)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .border(Color.white, width: 1)
                        .padding(4)
                        .background(LinearGradient(gradient: Gradient(colors: [.clear, .black]), startPoint: .top, endPoint: .bottom))
                }
            }
            
            Text(movie.title)
                .font(.footnote)
                .lineLimit(2)
            
            Spacer()
        }
    }
}
