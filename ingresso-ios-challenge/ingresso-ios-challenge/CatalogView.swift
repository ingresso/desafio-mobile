//
//  CatalogView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI

struct CatalogView: View {
    
    private let gridItems = [GridItem(.flexible()), GridItem(.flexible())]
    @ObservedObject var catalogVM : CatalogViewModel = CatalogViewModel()

    
    var body: some View {
        NavigationView{
            ScrollView{
                LazyVGrid(columns: gridItems, spacing: 20) {
                    ForEach(catalogVM.catalogItems[0..<catalogVM.catalogItems.count]) { item in
                        MovieCellView(currentItem: item)
                    }
                }
            }
            .navigationTitle("Filmes")
        }
    }
}








struct CatalogView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogView().previewDevice("iPhone 12")
    }
}
