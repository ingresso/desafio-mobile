//
//  CatalogView.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 28/08/21.
//

import SwiftUI

struct CatalogView: View {
    
    @ObservedObject var catalogVM : CatalogViewModel = CatalogViewModel()
    private let gridItems = [GridItem(.flexible()), GridItem(.flexible())]
    
    init(){
        UINavigationBar.appearance()
            .largeTitleTextAttributes = [.foregroundColor : UIColor.init(Color(#colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)))]
    }
    
    
    var body: some View {
        NavigationView{
            ScrollView{
                LazyVGrid(columns: gridItems, spacing: 20) {
                    ForEach(catalogVM.catalogItemsAvailableSoon[0..<catalogVM.catalogItemsAvailableSoon.count]) { item in
                        MovieCellView(currentItem: item)
                            .padding(.top, 20)
                            
                    }
                }
            }
            .navigationTitle("Filmes")
        }
        .environment(\.colorScheme, .dark)
    }
}




struct CatalogView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogView().previewDevice("iPhone 12")
    }
}
