//
//  ContentView.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import SwiftUI

struct MoviesView: View {
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible()),
    ]
    
    init() {
        // Config navigation bar title color
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor: UIColor.white]
    }
    
    var body: some View {
        NavigationView {
            ScrollView(showsIndicators: false) {
                LazyVGrid(columns: columns, spacing: 16) {
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                    MovieCell()
                }.padding(.horizontal, 4)
            }
            .navigationTitle("Filmes")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarItems(trailing: Button(action: {}) {
                Image(systemName: "magnifyingglass")
                    .foregroundColor(Color(.primaryBlue))
            })
            .background(Color(.primaryGray).ignoresSafeArea())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        MoviesView()
    }
}
