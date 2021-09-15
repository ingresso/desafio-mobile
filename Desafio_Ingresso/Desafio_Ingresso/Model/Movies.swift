//
//  Movies.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 13/09/21.
//

struct Movies: Codable, Hashable {
    let items: [Movie]
    let count: Int
}

struct Movie: Codable, Hashable {
    let id: String
    let title: String
    var images: [ImageInfo]
    let premiereDate: PremiereDate?
}

struct ImageInfo: Codable, Hashable {
    let url: String
    let type: ImageType
}

enum ImageType: String, Codable, Hashable {
    case posterHorizontal = "PosterHorizontal"
    case posterPortrait = "PosterPortrait"
}

struct PremiereDate: Codable, Hashable {
    let dayAndMonth: String
    let year: String
    let localDate: String
}
