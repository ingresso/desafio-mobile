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
    var images: [ImageInfo]
    let premiereDate: PremiereDate?
}

struct ImageInfo: Codable {
    let url: String
    let type: ImageType
}

enum ImageType: String, Codable {
    case posterHorizontal = "PosterHorizontal"
    case posterPortrait = "PosterPortrait"
}

struct PremiereDate: Codable {
    let dayAndMonth: String
    let year: String
}
