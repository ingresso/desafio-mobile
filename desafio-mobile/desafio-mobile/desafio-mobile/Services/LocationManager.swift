//
//  LocationManager.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 09/05/26.
//

import Foundation
import CoreLocation
import SwiftUI
import Combine
import MapKit

class LocationManager: NSObject, ObservableObject, CLLocationManagerDelegate {
    private let manager = CLLocationManager()
    
    @Published var currentCity: String?
    @Published var authorizationStatus: CLAuthorizationStatus = .notDetermined
    
    override init() {
        super.init()
        manager.delegate = self
        manager.desiredAccuracy = kCLLocationAccuracyHundredMeters
        self.authorizationStatus = manager.authorizationStatus
    }
    
    // Pede permissão na primeira vez
    func requestPermission() {
        manager.requestWhenInUseAuthorization()
    }
    
    // Leva pros Ajustes caso o usuário tenha negado e queira ativar depois
    func openSettings() {
        if let url = URL(string: UIApplication.openSettingsURLString) {
            UIApplication.shared.open(url)
        }
    }
    
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        // Atualiza o status na thread principal para a UI reagir na hora
        DispatchQueue.main.async {
            self.authorizationStatus = manager.authorizationStatus
            
            // Se o usuário revogar o acesso, limpa a memória e não continua herdando a ult localização que influencia no EventDetailView
            if self.authorizationStatus == .denied || self.authorizationStatus == .restricted || self.authorizationStatus == .notDetermined {
                self.currentCity = nil
            }
        }
        
        // Se permitiu, pede a localização atual
        if manager.authorizationStatus == .authorizedWhenInUse || manager.authorizationStatus == .authorizedAlways {
            manager.requestLocation()
        }
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.last else { return }
        getCityName(for: location)
    }
    
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("Erro de GPS: \(error.localizedDescription)")
    }
    
    // Traduz Coordenada -> Nome da Cidade
    private func getCityName(for location: CLLocation) {
        Task {
            if let request = MKReverseGeocodingRequest(location: location) {
                do {
                    
                    let mapItems = try await request.mapItems
                    
                    // O .first de um array SIM é opcional, então nele mantemos o 'if let'
                    if let firstItem = mapItems.first {
                        
                        if let shortAddress = firstItem.address?.shortAddress {
                            await MainActor.run {
                                self.currentCity = shortAddress
                            }
                        } else if let fullAddress = firstItem.address?.fullAddress {
                            await MainActor.run {
                                self.currentCity = fullAddress
                            }
                        }
                    }
                } catch {
                    print("Erro no MapKit: \(error.localizedDescription)")
                }
            }
        }
    }
}
