//
//  CatalogView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI

struct CatalogView: View {
    
    var catalogVM = CatalogViewModel()
    
    private let gridItems = [GridItem(.flexible()), GridItem(.flexible()), GridItem(.flexible())]
    
    var body: some View {
        NavigationView{
            ScrollView{
                LazyVGrid(columns: gridItems, spacing: 20) {
                    ForEach(0..<5) { _ in
                        //MovieCellView()
                    }
                }
                
            } .navigationTitle("Filmes")
        }
    }
}








struct CatalogView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogView().previewDevice("iPhone 12")
    }
}
