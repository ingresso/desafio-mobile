//
//  MovieCardView.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//

import SwiftUI

struct MovieCardView: View {
    let event: Event
    @ObservedObject var viewModel: EventViewModel
    
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            
            ZStack(alignment: .topTrailing) {
                // O Poster
                EventPosterImage(urlString: event.posterURL, width: 110, height: 170)
                
                HStack(alignment: .top) {
                    
                    // Classificação indicativa
                    if let rating = event.ratingDetails, let label = rating.label, !label.isEmpty {
                        AgeRatingBadge(rating: rating)
                            .scaleEffect(0.7)
                            .shadow(radius: 4)
                    }
                    
                    Spacer()
                    
                    
                    FavoriteButton(event: event, viewModel: viewModel)
                }
                .padding(8)
                
                if event.inPreSale {
                    VStack {
                        Spacer()
                        
                        Text("PRÉ-VENDA")
                            .font(.system(size: 8, weight: .bold, design: .rounded))
                            .kerning(1.0)
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 10)
                            .glassEffect(.regular, in: .rect(
                                topLeadingRadius: 0, bottomLeadingRadius: 10,
                                bottomTrailingRadius: 10,
                                topTrailingRadius: 0
                            ))
                            .overlay(
                                
                                Rectangle()
                                    .frame(height: 0.5)
                                    .foregroundColor(Color.white.opacity(0.3)),
                                alignment: .top
                            )
                    }
                    
                }
                
            }
            .frame(width: 110, height: 170)
            
            
            Text(event.title)
                .font(.caption)
                .bold()
                .lineLimit(3, reservesSpace: true)
                .multilineTextAlignment(.leading)
                .minimumScaleFactor(0.8)
                .foregroundColor(.primary)
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding(.horizontal, 10)
                .padding(.bottom, 12)
        }
    }
}
