//
//  ProfileView.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 09/05/26.
//

import SwiftUI

struct ProfileView: View {
    @Environment(\.dismiss) var dismiss
    @StateObject var profileVM = ProfileViewModel()
    @State private var nameInput: String = ""
    @ObservedObject var viewModel: EventViewModel
    var body: some View {
        NavigationStack {
            ZStack {
                // Fundo combinando com o app
                LinearGradient(gradient: Gradient(colors: [.degradeDark, .black]), startPoint: .top, endPoint: .bottom)
                    .ignoresSafeArea()
                
                VStack(spacing: 30) {
                    if profileVM.isLoggedIn {
                        // Estado - logado
                        VStack(spacing: 20) {
                            Image(systemName: "person.crop.circle.fill")
                                .resizable()
                                .frame(width: 100, height: 100)
                                .foregroundColor(.blue)
                            
                            Text("Olá, \(profileVM.userName)!")
                                .font(.system(size: 28, weight: .bold, design: .rounded))
                            
                            Text("Bem-vindo de volta ao seu cinema particular.")
                                .font(.subheadline)
                                .foregroundColor(.secondary)
                                .multilineTextAlignment(.center)
                            
                            Button(role: .destructive) {
                                profileVM.logout()
                                viewModel.clearFavorites()
                            } label: {
                                Text("Sair da Conta")
                                    .fontWeight(.semibold)
                            }
                            .padding(.top, 40)
                        }
                        .padding(40)
                        .background(.ultraThinMaterial)
                        .cornerRadius(24)
                        
                    } else {
                        //Estado - registro
                        VStack(alignment: .leading, spacing: 20) {
                            Text("Crie sua conta")
                                .font(.system(size: 32, weight: .bold, design: .rounded))
                            
                            Text("Salve seus favoritos e receba recomendações personalizadas.")
                                .font(.subheadline)
                                .foregroundColor(.secondary)
                            
                            TextField("Como quer ser chamado?", text: $nameInput)
                                .padding()
                                .background(Color.white.opacity(0.1))
                                .cornerRadius(12)
                                .overlay(RoundedRectangle(cornerRadius: 12).stroke(Color.white.opacity(0.2)))
                            
                            Button {
                                if !nameInput.isEmpty {
                                    profileVM.register(name: nameInput)
                                }
                            } label: {
                                Text("Começar agora")
                                    .fontWeight(.bold)
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .background(nameInput.isEmpty ? Color.gray : Color.blue)
                                    .foregroundColor(.white)
                                    .cornerRadius(12)
                            }
                            .disabled(nameInput.isEmpty)
                        }
                        .padding(30)
                    }
                }
                .padding()
            }
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button("Fechar") { dismiss() }
                }
            }
        }
    }
}
