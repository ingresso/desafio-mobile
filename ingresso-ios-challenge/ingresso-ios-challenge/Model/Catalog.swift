//
//  Catalog.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 27/08/21.
//

import Foundation
import Alamofire

// MARK: - Catalog
public struct Catalog: Codable {
    public let items: [Item]
    public let count: Int
    
    public init(items: [Item], count: Int) {
        self.items = items
        self.count = count
    }
}

// MARK: - Item
public struct Item: Codable {
    public let id, title, originalTitle: String
    public let movieIdUrl: String
    public let ancineId, countryOrigin: String
    public let priority: Int
    public let contentRating, duration: String
    public let rating: Int
    public let synopsis, cast, director, distributor: String
    public let inPreSale, isReexhibition: Bool
    public let urlKey: String
    public let isPlaying: Bool
    public let countIsPlaying: Int
    public let premiereDate: PremiereDate? = PremiereDate(localDate: nil,
                                                          isToday: false,
                                                          dayOfWeek: "",
                                                          dayAndMonth: "",
                                                          hour: "",
                                                          year: "")
    public let creationDate: String
    public let city: String
    public let siteUrl, nationalSiteUrl: String
    public let images: [Image]
    public let genres: [String]
    public let trailers: [Trailer]
    public let rottenTomatoe: RottenTomatoe?
    
    enum CodingKeys: String, CodingKey {
        case id, title, originalTitle, movieIdUrl, ancineId, countryOrigin, priority, contentRating, duration, rating, synopsis, cast, director, distributor, inPreSale, isReexhibition, urlKey, isPlaying, countIsPlaying, premiereDate, creationDate, city
        case siteUrl = "siteURL"
        case nationalSiteUrl = "nationalSiteURL"
        case images, genres, trailers, rottenTomatoe
    }
    
    init(id: String, title: String, originalTitle: String, movieIdUrl: String, ancineId: String, countryOrigin: String, priority: Int, contentRating: String, duration: String, rating: Int, synopsis: String, cast: String, director: String, distributor: String, inPreSale: Bool, isReexhibition: Bool, urlKey: String, isPlaying: Bool, countIsPlaying: Int, creationDate: String, city: String, siteUrl: String, nationalSiteUrl: String, images: [Image], genres: [String], trailers: [Trailer], rottenTomatoe: RottenTomatoe?) {
        self.id = id
        self.title = title
        self.originalTitle = originalTitle
        self.movieIdUrl = movieIdUrl
        self.ancineId = ancineId
        self.countryOrigin = countryOrigin
        self.priority = priority
        self.contentRating = contentRating
        self.duration = duration
        self.rating = rating
        self.synopsis = synopsis
        self.cast = cast
        self.director = director
        self.distributor = distributor
        self.inPreSale = inPreSale
        self.isReexhibition = isReexhibition
        self.urlKey = urlKey
        self.isPlaying = isPlaying
        self.countIsPlaying = countIsPlaying
        self.creationDate = creationDate
        self.city = city
        self.siteUrl = siteUrl
        self.nationalSiteUrl = nationalSiteUrl
        self.images = images
        self.genres = genres
        self.trailers = trailers
        self.rottenTomatoe = rottenTomatoe
    }
}

// MARK: - Image
public struct Image: Codable {
    public let url: String
    public let type: String
    
    public init(url: String, type: String) {
        self.url = url
        self.type = type
    }
}

// MARK: - PremiereDate
public struct PremiereDate: Codable {
    public let localDate: Date?
    public let isToday: Bool?
    public let dayOfWeek, dayAndMonth, hour, year: String?
    
}

// MARK: - RottenTomatoe
public struct RottenTomatoe: Codable {
    public let id, criticsRating, criticsScore, audienceRating: String
    public let audienceScore: String
    public let originalUrl: String
    
    
}

// MARK: - Trailer
public struct Trailer: Codable {
    public let type: String
    public let url: String
    public let embeddedUrl: String
    
    
}
