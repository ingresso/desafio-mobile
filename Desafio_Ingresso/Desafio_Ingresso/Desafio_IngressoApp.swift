//
//  Desafio_IngressoApp.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 12/09/21.
//

import SwiftUI

@main
struct Desafio_IngressoApp: App {
    
    @StateObject private var viewModel = ViewModel()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(viewModel)
        }
    }
}
