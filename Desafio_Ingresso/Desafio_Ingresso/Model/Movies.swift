//
//  Movies.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

struct Movies: Codable {
    let items: [Movie]
}

struct Movie: Codable {
    let id: String
    let title: String
    let premiereDate: PremiereDate?
}

struct PremiereDate: Codable {
    let dayAndMonth: String
    let year: String
}
