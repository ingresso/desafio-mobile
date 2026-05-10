//
//  LaunchScreenView.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 08/05/26.
//


import SwiftUI

struct LaunchScreenView: View {
    @State private var imageScale: CGFloat = 0.0
    @State private var imageOpacity: Double = 0.0
    var onAnimationFinished: () -> Void
    
    var body: some View {
        ZStack {
            
            
            AnimatedGradient()
                .transition(.opacity)
            
            Image("LaunchAnima1")
                .resizable()
                .scaledToFit()
                .frame(width: 150)
                .scaleEffect(imageScale)
                .opacity(imageOpacity)
        }
        .onAppear {
            
            withAnimation(.spring(response: 0.5, dampingFraction: 0.6).delay(0.8)) {
                imageScale = 1.2
                imageOpacity = 1.2
            }
            
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.9) {
                onAnimationFinished()
            }
        }
    }
}


@available(iOS 16.0, *)
struct AnimatedGradient: View {
    @State private var animate = false
    
    var body: some View {
        LinearGradient(
            colors: animate ? [Color.degradeLaunchDark, Color.degradeLaunchLight, Color.degradeLaunchLight] : [Color.degradeLaunchLight, Color.degradeLaunchLight, Color.degradeLaunchDark],
            startPoint: .bottomTrailing,
            endPoint:.top
        )
        .onAppear {
            withAnimation(.linear(duration: 3.7).repeatForever(autoreverses: true)) {
                animate.toggle()
            }
        }
        .ignoresSafeArea()
    }
}


#Preview {
    LaunchScreenView(onAnimationFinished: {})
}
