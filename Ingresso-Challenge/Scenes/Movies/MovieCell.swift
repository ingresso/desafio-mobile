//
//  MovieCell.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import SwiftUI

struct MovieCell: View {
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack {
                Image(systemName: "film")
                
                VStack {
                    Spacer()
                    Text("09/09/21")
                        .fontWeight(.medium)
                        .foregroundColor(.white)
                }
            }
            .background(Color(.imageBackgroundGray))
            .frame(idealHeight: 150, alignment: .center)
            
            Text("Garota Inflamavel asdasudhasud ashdakshd")
                .font(.footnote)
                .lineLimit(2)
        }
    }
}
