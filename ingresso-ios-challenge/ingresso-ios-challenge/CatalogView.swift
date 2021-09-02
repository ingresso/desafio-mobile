//
//  CatalogView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI

struct CatalogView: View {
    
    var catalogVM = CatalogViewModel()
    
    var body: some View {
        ZStack{
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
 
            Button(action: {
                catalogVM.fetchCatalogs()
            })
            {
                Text("Login")
                    .foregroundColor(.white)
                    .font(.system(size: 24, weight: .medium))
            }.frame(maxWidth: .infinity)
            .padding(.vertical, 20)
            .background(Color.blue.opacity(0.9))
            .cornerRadius(10)
            .padding(.horizontal, 15)
                
            
        }
    }
}







struct CatalogView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogView().previewDevice("iPhone 12")
    }
}
