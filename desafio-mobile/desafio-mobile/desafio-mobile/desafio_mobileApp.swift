//
//  desafio_mobileApp.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//

import SwiftUI

@main
struct desafio_mobileApp: App {
    @State var showingLaunchScreen = true
    var body: some Scene {
        WindowGroup {
            ZStack {
                if showingLaunchScreen {
                    
                    LaunchScreenView(onAnimationFinished: {
                        
                        withAnimation(.easeOut(duration: 0.8)) {
                            showingLaunchScreen = false
                        }
                    })
                    .transition(.opacity)
                    
                } else {
                    
                    ContentView()
                        .preferredColorScheme(.dark)
                        .transition(.opacity)
                }
            }
        }
    }
}
