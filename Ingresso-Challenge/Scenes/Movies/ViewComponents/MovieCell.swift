//
//  MovieCell.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import SwiftUI

struct MovieCell: View {
    
    var movie: Movie
    
    init(_ movie: Movie) {
        self.movie = movie
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack {
                if let imageString = movie.imageURL {
                    if let data = try? Data(contentsOf: URL(string: imageString)!) {
                        Image(uiImage: UIImage(data: data) ?? UIImage(named: "placeholder")!)
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    }
                    else {
                        Image("placeholder")
                            .resizable()
                            .aspectRatio(contentMode: .fit)
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
