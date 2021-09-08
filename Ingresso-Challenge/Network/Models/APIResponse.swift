//
//  APIResponse.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

// MARK: - APIResponse
struct APIResponse: Codable {
    let items: [Item]
    let count: Int
}

// MARK: - Item
struct Item: Codable {
    let id, title, originalTitle: String
    let movieIdUrl: String
    let ancineId, countryOrigin: String
    let priority: Int
    let contentRating: String
    let duration: String
    let rating: Int
    let synopsis, cast, director, distributor: String
    let inPreSale, isReexhibition: Bool
    let urlKey: String
    let isPlaying: Bool
    let countIsPlaying: Int
    let premiereDate: PremiereDate?
    let creationDate: String
    let city: String
    let siteURL, nationalSiteURL: String
    let images: [APIImage]
    let genres, ratingDescriptors: [String]
    let completeTags: [CompleteTag]
    let tags: [String]
    let trailers: [Trailer]
    let boxOfficeId, partnershipType: Data?
    let rottenTomatoe: RottenTomatoe?
}

// MARK: - Image
struct APIImage: Codable {
    let url: String
    let type: ImageType
}

enum ImageType: String, Codable {
    case posterHorizontal = "PosterHorizontal"
    case posterPortrait = "PosterPortrait"
}

// MARK: - PremiereDate
struct PremiereDate: Codable {
    let localDate: String
    let isToday: Bool
    let dayOfWeek: String
    let dayAndMonth: String
    let hour: String
    let year: String
}

// MARK: - RottenTomatoe
struct RottenTomatoe: Codable {
    let id: String
    let criticsRating: String
    let criticsScore: String
    let audienceRating: String
    let audienceScore: String
    let originalUrl: String
}

// MARK: - Trailer
struct Trailer: Codable {
    let type: String // Plataform where trailer is hosted, ie. Youtube
    let url: String
    let embeddedUrl: String
}

// MARK: - CompleteTag
struct CompleteTag: Codable {
    let name: String
    let background: String
    let color: String
}
