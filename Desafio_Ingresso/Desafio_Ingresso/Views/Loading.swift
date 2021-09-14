//
//  Loading.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

import SwiftUI

struct Loading: View {
    var body: some View {
        
        ZStack {
            Color(.darkGray)
                .ignoresSafeArea()
            
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
                    .padding()
                    .scaleEffect(2)
                
                Text("Carregando...")
                    .bold()
                    .foregroundColor(.white)
            }
        }
    }
}

struct Loading_Previews: PreviewProvider {
    static var previews: some View {
        Loading()
    }
}
