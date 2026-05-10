//
//  ContentView.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//
import SwiftUI
struct ContentView: View {
    @StateObject private var viewModel = EventViewModel()
    @StateObject private var locationManager = LocationManager()
    @State private var searchText = ""
    @State private var isSearching = false
    @FocusState private var isSearchFocused: Bool
    @State private var currentTab = "Em Breve"
    @Namespace var animation
    @State private var showProfile = false
    
    var emptyStateContent: (icon: String, message: String) {
        if !searchText.isEmpty {
            return ("magnifyingglass", "Não encontramos nenhum filme com o nome '\(searchText)'.")
        }
        
        switch currentTab {
        case "Favoritos":
            return ("star", "Você ainda não tem filmes favoritos.")
        case "Últ. Estreias":
            return ("film", "Não há estreias recentes para exibir neste momento.")
        case "Em Breve":
            return ("calendar", "Não há filmes previstos para os próximos meses.")
        default:
            return ("questionmark.circle", "Nenhum filme encontrado.")
        }
    }
    var filteredEvents: [Event] {
        let today = Date()
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        let todayString = formatter.string(from: today)
        
        return viewModel.events.filter { event in
            // Pega exatamente a string "2026-05-07"
            let eventDate = String(event.premiereDate?.localDate?.prefix(10) ?? "")
            
            // Filtro da barra de busca
            if !searchText.isEmpty {
                guard event.title.localizedCaseInsensitiveContains(searchText) else { return false }
            }
            
            switch currentTab {
            case "Últ. Estreias":
                
                return !eventDate.isEmpty && eventDate <= todayString
                
            case "Em Breve":
                
                return eventDate > todayString || eventDate.isEmpty
                
            case "Favoritos":
                return viewModel.isFavorite(event)
                
            default:
                return true
            }
        }
    }
    var groupedEvents: [(key: String, value: [Event])] {
        
        //Filmes filtrados em Estreias ou Favoritos
        let dictionary = Dictionary(grouping: filteredEvents) { event -> String in
            if let localDate = event.premiereDate?.localDate, localDate.count >= 7 {
                return String(localDate.prefix(7))
            }
            return "9999-12"
        }
        
        
        let sortedKeys = dictionary.keys.sorted()
        
        
        return sortedKeys.map { key in
            let displayTitle = key == "9999-12" ? "Em breve" : key.formatToMonthYear()
            
            // Ordenamos os filmes deste mês específico por data de estreia
            let sortedMoviesForMonth = (dictionary[key] ?? []).sorted {
                let date1 = $0.premiereDate?.localDate ?? ""
                let date2 = $1.premiereDate?.localDate ?? ""
                return date1 < date2 // Ordem Crescente
            }
            
            return (key: displayTitle, value: sortedMoviesForMonth)
        }
    }
    
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
    
    
    
    var body: some View {
        TabView {
            
            NavigationStack {
                ZStack {
                    
                    LinearGradient(gradient: Gradient(colors: [.degradeDark, .black]),
                                   startPoint: .top,
                                   endPoint: .bottom)
                    .ignoresSafeArea()
                    
                    
                    ScrollView {
                        
                        LazyVStack(spacing: 0, pinnedViews: [.sectionHeaders]) {
                            
                            
                            
                            
                            // Seções
                            Section(header:
                                        VStack(spacing: 0) {
                                HStack(spacing: 35) {
                                    FilterTabButton(title: "Últ. Estreias", current: $currentTab, animation: animation)
                                    FilterTabButton(title: "Em Breve", current: $currentTab, animation: animation)
                                    FilterTabButton(title: "Favoritos", current: $currentTab, animation: animation)
                                }
                                .padding(.vertical, 10)
                                .padding(.horizontal)
                                .frame(maxWidth: 365)
                                .glassEffect(.regular.interactive(), in: .capsule )
                                .padding(10)
                                
                                
                            }
                            ) {
                                // Conteúdo
                                if viewModel.isLoading {
                                    ProgressView("Carregando filmes...")
                                        .padding(.top, 100)
                                } else if filteredEvents.isEmpty {
                                    
                                    let content = emptyStateContent
                                    EmptyStateView(icon: content.icon, message: content.message)
                                        .padding(.top, 100)
                                } else {
                                    
                                    // Espaçamento entre os meses
                                    LazyVStack(spacing: 30) {
                                        ForEach(groupedEvents, id: \.key) { group in
                                            VStack(alignment: .leading, spacing: 14) {
                                                Text(group.key)
                                                    .font(.title2)
                                                    .fontWeight(.bold)
                                                    .padding(.horizontal)
                                                
                                                MovieFlatCarossel(events: group.value, viewModel: viewModel, locationManager: locationManager)
                                                    .id("\(currentTab)-\(group.key)")
                                            }
                                        }
                                    }
                                    .padding(.vertical, 20)
                                }
                            }
                        }
                    }
                    .refreshable {
                        await viewModel.loadEvents()
                    }
                }
                .navigationTitle(isSearching ? "" : "Filmes")
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    if isSearching {
                        
                        // O Campo de Texto assume o lugar do Título
                        ToolbarItem(placement: .principal) {
                            HStack {
                                Image(systemName: "magnifyingglass")
                                    .foregroundColor(.gray)
                                
                                TextField("Buscar...", text: $searchText)
                                    .focused($isSearchFocused)
                                    .textInputAutocapitalization(.never)
                                
                                if !searchText.isEmpty {
                                    Button(action: {
                                        searchText = ""
                                    }) {
                                        Image(systemName: "xmark.circle.fill")
                                            .foregroundColor(.gray)
                                    }
                                }
                            }
                            .padding(.horizontal, 12)
                            .padding(.vertical, 8)
                            .glassEffect()
                            .cornerRadius(10)
                            .frame(width: 260)
                        }
                        
                        // Botão Cancelar no canto direito
                        ToolbarItem(placement: .topBarTrailing) {
                            Button("Cancelar") {
                                withAnimation(.spring(response: 0.3, dampingFraction: 0.7)) {
                                    isSearching = false
                                    searchText = ""
                                    isSearchFocused = false
                                }
                            }
                            .font(.system(size: 16, weight: .medium))
                            .foregroundColor(.blue)
                        }
                        
                    } else {
                        
                        ToolbarItem(placement: .topBarLeading) {
                            LocationButton(locationManager: locationManager)
                        }
                        
                        ToolbarItem(placement: .topBarTrailing) {
                            // Agrupando Lupa e Conta
                            HStack(spacing: 16) {
                                Button(action: {
                                    withAnimation(.spring(response: 0.3, dampingFraction: 0.7)) {
                                        isSearching = true
                                        isSearchFocused = true
                                    }
                                }) {
                                    Image(systemName: "magnifyingglass")
                                        .font(.system(size: 18, weight: .semibold))
                                        .foregroundColor(.white)
                                }
                                
                                AccountButton(showProfile: $showProfile)
                            }
                            .padding(.horizontal, 16)
                            .padding(.vertical, 8)
                            
                        }
                        
                    }
                    
                }
                
                
                .fullScreenCover(isPresented: $showProfile) {
                    ProfileView(viewModel: viewModel)
                }
                .onAppear {
                    
                    locationManager.requestPermission()
                }
                .task {
                    if viewModel.events.isEmpty {
                        await viewModel.loadEvents()
                    }
                }
            } 
            .onTapGesture {
                hideKeyboard()
            }
            .tabItem { Label("Filmes", systemImage: "film.fill") }
            
            // TabBar
            NavigationStack { Text("Destaques") }.tabItem { Label("Destaques", systemImage: "star") }
            NavigationStack { Text("Cinemas") }.tabItem { Label("Cinemas", systemImage: "mappin.and.ellipse") }
            NavigationStack { Text("Notícias") }.tabItem { Label("Notícias", systemImage: "newspaper") }
            NavigationStack { Text("Prevenções") }.tabItem { Label("Prevenções", systemImage: "shield.checkerboard") }
        }
        .accentColor(.blue)
        
    }
    
}



#Preview {
    ContentView()
}
