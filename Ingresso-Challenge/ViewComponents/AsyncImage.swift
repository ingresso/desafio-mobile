//
//  AsyncImage.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 05/09/21.
//

import SwiftUI
import URLImage

struct AsyncImage: View {
    
    var imageString: String?
    
    var body: some View {
        if let imageString = imageString, let imageURL = URL(string: imageString) {
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
    }
}
