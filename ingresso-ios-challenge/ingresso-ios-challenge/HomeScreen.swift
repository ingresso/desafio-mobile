//
//  HomeScreen.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 08/09/21.
//

import SwiftUI
import Kingfisher

struct HomeScreen: View {
    
    @State private var isActive:Bool = false
    
    var body: some View {
        ZStack{
            Color(.black)
                .ignoresSafeArea()
            
            VStack(alignment: .center) {
                if self.isActive {
                    CatalogView()
                } else {
                    Text("Carregando")
                        .font(Font.largeTitle)
                        .foregroundColor(.white)
               
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
                    .scaleEffect(2)
                }
                
            }
            .onAppear {
                
                DispatchQueue.main.asyncAfter(deadline: .now() + 4) {
                    withAnimation {
                        self.isActive = true
                    }
                }
            }
        }
    }
    
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
