//
//  Components.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 06/05/26.
//

import SwiftUI
import CoreLocation
import Kingfisher

struct EventPosterImage: View {
    let urlString: String?
    var width: CGFloat? = nil
    var height: CGFloat? = nil
    var contentMode: SwiftUI.ContentMode = .fill
    var body: some View {
        Group {
            if let urlString = urlString, let url = URL(string: urlString) {
                
                // KingFisher que está pegando a url agr, ao em vez do Async
                KFImage(url)
                    .placeholder {
                        ZStack {
                            Color.gray.opacity(0.1)
                            ProgressView()
                        }
                    }
                    .fade(duration: 0.25) // Modificador do Kingfisher!
                    .resizable()
                    .aspectRatio(contentMode: contentMode)
                
            } else {
                PlaceholderCard(width: width, height: height)
            }
        }
        .frame(width: width, height: height)
        .cornerRadius(7)
        .clipped()
    }
}
struct PlaceholderCard: View {
    var width: CGFloat?
    var height: CGFloat?
    
    var body: some View {
        RoundedRectangle(cornerRadius: 8)
            .fill(Color.gray.opacity(0.2))
            .frame(width: width, height: height)
            .overlay(
                Image(systemName: "film")
                    .foregroundColor(.gray)
            )
    }
}
struct PremiereBadge: View {
    let dateText: String
    
    var body: some View {
        HStack(spacing: 6) {
            Image(systemName: "calendar")
                .font(.system(size: 12, weight: .bold))
            
            Text("Estreia \(dateText)")
                .font(.system(size: 12, weight: .bold, design: .rounded))
                .textCase(.uppercase)
        }
        .foregroundColor(.white)
        .padding(.horizontal, 10)
        .padding(.vertical, 5)
        .background(
            Capsule()
            
                .fill(Color.green.opacity(0.9))
        )
    }
}
struct SectionHeader: View {
    let title: String
    
    var body: some View {
        Text(title)
            .font(.headline)
            .fontWeight(.bold)
            .padding(.top, 10)
    }
}
struct GenreBadge: View {
    let text: String
    
    var body: some View {
        Text(text)
            .font(.caption2)
            .fontWeight(.semibold)
            .padding(.horizontal, 10)
            .padding(.vertical, 5)
            .background(Color.secondary.opacity(0.15))
            .cornerRadius(12)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(Color.secondary.opacity(0.1), lineWidth: 1)
            )
    }
}
struct TrailerButton: View {
    let url: URL
    
    
    @Environment(\.openURL) private var openURL
    
    var body: some View {
        Button {
            openURL(url)
        } label: {
            HStack {
                Image(systemName: "play.rectangle.fill")
                Text("Trailer")
            }
            .font(.headline)
            .frame(maxWidth: .infinity)
            .padding(.vertical, 14)
            .glassEffect(.regular.interactive().tint(.red.opacity(0.8)), in: .capsule )
            .foregroundColor(.white)
            .cornerRadius(12)
        }
    }
}
struct PrimaryButton: View {
    let title: String
    var icon: String? = nil
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack {
                if let icon = icon {
                    Image(systemName: icon)
                }
                Text(title)
            }
            .fontWeight(.bold)
            .frame(maxWidth: .infinity)
            .padding()
            .glassEffect(.regular.interactive().tint(.blue), in: .capsule )
            .foregroundColor(.white)
            
            .cornerRadius(12)
            
        }
    }
}
struct FilterTabButton: View {
    let title: String
    @Binding var current: String
    var animation: Namespace.ID
    
    var body: some View {
        Button {
            withAnimation(.spring(response: 0.3, dampingFraction: 0.7)) {
                current = title
            }
        } label: {
            VStack(spacing: 6) {
                Text(title)
                    .font(.system(size: 14, weight: current == title ? .bold : .medium))
                    .foregroundColor(current == title ? .blue : .gray)
                
                if current == title {
                    Capsule()
                        .fill(Color.blue)
                        .frame(height: 3)
                        .matchedGeometryEffect(id: "tab_underline", in: animation)
                } else {
                    Capsule()
                        .fill(Color.clear)
                        .frame(height: 3)
                }
            }
        }
    }
}

struct EmptyStateView: View {
    let icon: String
    let message: String
    
    var body: some View {
        VStack(spacing: 12) {
            Spacer()
            Image(systemName: icon)
                .font(.system(size: 40))
                .foregroundColor(.gray)
            Text(message)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
                .padding(.horizontal)
            Spacer()
        }
    }
}
struct AgeRatingBadge: View {
    let rating: RatingDetails
    
    var body: some View {
        Text(rating.label ?? "L")
            .font(.system(size: 12, weight: .bold, design: .rounded))
            .foregroundColor(.white)
            .frame(width: 24, height: 24)
            .background(
                RoundedRectangle(cornerRadius: 4)
                // Usa a cor que veio da API através da nossa extensão!
                    .fill(Color(hex: rating.color ?? "#4CAF50"))
            )
        // Se o usuário quiser ler, o VoiceOver vai ler o nome completo ("6 anos")
            .accessibilityLabel(rating.name ?? "Livre")
    }
}

struct FavoriteButton: View {
    let event: Event
    @ObservedObject var viewModel: EventViewModel
    var size: Font = .body
    var padding: CGFloat = 6
    
    
    @State private var isFav: Bool = false
    
    var body: some View {
        Button {
            
            let impact = UIImpactFeedbackGenerator(style: .soft)
            impact.impactOccurred(intensity: 0.6)
            
            
            withAnimation(.spring(response: 0.3, dampingFraction: 0.6)) {
                isFav.toggle()
            }
            
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.10) {
                viewModel.toggleFavorite(event: event)
            }
            
        } label: {
            Image(systemName: isFav ? "star.fill" : "star")
                .font(size)
                .foregroundColor(isFav ? .yellow : .white)
                .padding(padding)
                .glassEffect(.regular.interactive(), in: .circle)
                .clipShape(Circle())
                .scaleEffect(isFav ? 0.9 : 0.8)
        }
        .onAppear {
            // Sincroniza o botão quando ele entra na tela
            isFav = viewModel.favoriteIDs.contains(event.id)
        }
        .onChange(of: viewModel.favoriteIDs) { oldValue, newValue in
            
            isFav = newValue.contains(event.id)
        }
    }
}
struct MovieFlatCarossel: View {
    let events: [Event]
    @ObservedObject var viewModel: EventViewModel
    @ObservedObject var locationManager: LocationManager
    // Estado para controlar o índice e o arrasto
    @State private var currentIndex: Int = 0
    @State private var dragOffset: CGFloat = 0
    
    
    let cardWidth: CGFloat = 110
    let cardHeight: CGFloat = 230
    let spacing: CGFloat = 80
    
    // Fatores de Destaque
    let focusScale: CGFloat = 1.25
    let scaleSpread: CGFloat = 0.25
    let focusBlur: CGFloat = 2.0
    
    var body: some View {
        GeometryReader { geo in
            
            ZStack {
                if !events.isEmpty {
                    
                    
                    let isInfinite = events.count > 5
                    
                    
                    let displayRange = isInfinite ? Array((currentIndex - 5)...(currentIndex + 5)) : Array(0..<events.count)
                    
                    ForEach(displayRange, id: \.self) { virtualIndex in
                        
                        
                        let realIndex = isInfinite ? (virtualIndex % events.count + events.count) % events.count : virtualIndex
                        let event = events[realIndex]
                        
                        let diff = CGFloat(virtualIndex - currentIndex)
                        let dragFactor = dragOffset / spacing
                        let effectiveDiff = diff - dragFactor
                        let absEffectiveDiff = abs(effectiveDiff)
                        
                        if absEffectiveDiff <= 4.5 {
                            
                            let scale = max(focusScale - (absEffectiveDiff * scaleSpread), 0.75)
                            let blurRadius = min(absEffectiveDiff * 1.3, focusBlur)
                            let zIndex = Double(events.count) - absEffectiveDiff
                            let offset = (diff * spacing) + dragOffset
                            let shadowOpacity = max(0.4 - (absEffectiveDiff * 0.1), 0.1)
                            
                            NavigationLink(destination: EventDetailView(event: event, viewModel: viewModel, locationManager: locationManager)) {
                                MovieCardView(event: event, viewModel: viewModel)
                                    .blur(radius: blurRadius)
                                    .shadow(color: .black.opacity(shadowOpacity), radius: scale == focusScale ? 15 : 6, y: 8)
                                    .animation(.easeInOut(duration: 0.3), value: currentIndex)
                            }
                            .buttonStyle(PlainButtonStyle())
                            .disabled(virtualIndex != currentIndex)
                            .frame(width: cardWidth, height: cardHeight)
                            .scaleEffect(scale)
                            .offset(x: offset)
                            .offset(y: (cardHeight * (focusScale - 1)) / 2)
                            .zIndex(zIndex)
                            .animation(.interactiveSpring(response: 0.4, dampingFraction: 0.8), value: dragOffset)
                            .animation(.spring(response: 0.5, dampingFraction: 0.85), value: currentIndex)
                        }
                    }
                }
            }
            .onChange(of: events.count) { oldCount, newCount in
                
                if newCount == 0 {
                    currentIndex = 0
                } else if newCount <= 5 {
                    if currentIndex >= newCount {
                        withAnimation(.spring(response: 0.4, dampingFraction: 0.8)) {
                            currentIndex = newCount - 1
                        }
                    } else if currentIndex < 0 {
                        withAnimation(.spring(response: 0.4, dampingFraction: 0.8)) {
                            currentIndex = 0
                        }
                    }
                }
            }
            .frame(width: geo.size.width)
            
            // Container do gesto
            .background(Color.clear.contentShape(Rectangle()))
            .highPriorityGesture(
                DragGesture()
                    .onChanged { gesture in
                        dragOffset = gesture.translation.width
                    }
                    .onEnded { gesture in
                        let velocity = gesture.predictedEndTranslation.width / spacing
                        let threshold: CGFloat = velocity > 0.5 ? 0.3 : 0.7
                        let isInfinite = events.count > 5
                        
                        withAnimation(.spring(response: 0.5, dampingFraction: 0.82)) {
                            
                            // Flag
                            var indexChanged = false
                            
                            if gesture.translation.width < -spacing * threshold {
                                if isInfinite || currentIndex < events.count - 1 {
                                    currentIndex += 1
                                    indexChanged = true
                                }
                            } else if gesture.translation.width > spacing * threshold {
                                if isInfinite || currentIndex > 0 {
                                    currentIndex -= 1
                                    indexChanged = true
                                }
                            }
                            
                            // Haptic
                            if indexChanged {
                                let generator = UISelectionFeedbackGenerator()
                                generator.prepare()
                                generator.selectionChanged()
                            }
                            
                            dragOffset = 0
                        }
                    }
            )
        }
        // Altura total reservada para os cards maiores e escalas
        .frame(height: (cardHeight * focusScale) + 40)
    }
}
struct LocationButton: View {
    @ObservedObject var locationManager: LocationManager
    
    var body: some View {
        Button(action: {
            // Alert nativo da localização
            if locationManager.authorizationStatus == .notDetermined {
                locationManager.requestPermission()
            } else {
                // Se ele já autorizou (ou se negou antes), manda direto para os Ajustes
                locationManager.openSettings()
            }
        }) {
            // Lógica visual baseada na permissão
            if locationManager.authorizationStatus == .authorizedWhenInUse ||
                locationManager.authorizationStatus == .authorizedAlways {
                
                Image(systemName: "location")
                    .font(.system(size: 18, weight: .semibold))
                    .foregroundColor(.blue)
                
            } else {
                
                Image(systemName: "location.slash.fill")
                    .font(.system(size: 18, weight: .semibold))
                    .foregroundColor(.gray) 
                
            }
        }
        
    }
}
struct AccountButton: View {
    @Binding var showProfile: Bool
    var body: some View {
        Button(action: {
            showProfile.toggle() 
        }) {
            Image(systemName: "person.crop.circle")
                .font(.system(size: 20, weight: .medium))
                .foregroundColor(.white)
        }
    }
}
extension Color {
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        let a, r, g, b: UInt64
        switch hex.count {
        case 3: // RGB (12-bit)
            (a, r, g, b) = (255, (int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
        case 6: // RGB (24-bit)
            (a, r, g, b) = (255, int >> 16, int >> 8 & 0xFF, int & 0xFF)
        case 8: // ARGB (32-bit)
            (a, r, g, b) = (int >> 24, int >> 16 & 0xFF, int >> 8 & 0xFF, int & 0xFF)
        default:
            (a, r, g, b) = (255, 0, 0, 0)
        }
        self.init(.sRGB, red: Double(r) / 255, green: Double(g) / 255, blue:  Double(b) / 255, opacity: Double(a) / 255)
    }
}
