//
//  ContentView.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

struct ContentView: View {
    
    @State private var isLoading = false
    @EnvironmentObject var viewModel: ViewModel
    
    init() {
        UITabBar.appearance().isTranslucent = false
        UITabBar.appearance().barTintColor = UIColor(.secondary)
    }
    
    enum Tab {
        case destaques
        case filmes
        case cinemas
        case noticias
        case prevencoes
    }
    
    var body: some View {
        
        ZStack {
            
            if isLoading {
                Loading()
            }
            else {
                
                TabView {
                    ComingSoonMovies()
                        .tabItem {
                            Label("Filmes",systemImage: "film")
                        }
                        .tag(Tab.filmes)
                        
                    
                } .accentColor(.orange)
            }
        }
        .onAppear {
            isLoading = true
            viewModel.fetchAllMovies() {
                print(viewModel.movies)
                
                //Conta 3 segundos antes de apresentar a tela de filmes, só para podermos apreciar a beleza da tela de carregamento.
                DispatchQueue.main.asyncAfter(deadline: .now() + 3) {
                    isLoading = false
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ViewModel())
    }
}
