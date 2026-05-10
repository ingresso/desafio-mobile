//
//  ProfileViewModel.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 09/05/26.
//

import SwiftUI
import Combine

class ProfileViewModel: ObservableObject {
    
    @AppStorage("user_name") var userName: String = ""
    @AppStorage("is_logged_in") var isLoggedIn: Bool = false
    
    func register(name: String) {
        self.userName = name
        self.isLoggedIn = true
    }
    
    func logout() {
        self.userName = ""
        self.isLoggedIn = false
    }
}
