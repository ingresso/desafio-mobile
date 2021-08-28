//
//  Catalog.swift
//  ingresso-ios-challenge
//
//  Created by Phil on 27/08/21.
//

//   let catalog = try? newJSONDecoder().decode(Catalog.self, from: jsonData)

import Foundation
import Alamofire

// MARK: - Catalog
public struct Catalog: Decodable {
    public let items: [Item]

    public init(items: [Item]) {
        self.items = items
    }
}

// MARK: - Item
public struct Item: Decodable, Identifiable {
    public let id, title, originalTitle, movieIDURL: String
    public let premiereDate: PremiereDate
    public let city: String
    public let siteURL, nationalSiteURL: String
    public let images: [Image]

    public init(id: String, title: String, originalTitle: String, movieIDURL: String, premiereDate: PremiereDate, city: String, siteURL: String, nationalSiteURL: String, images: [Image]) {
        self.id = id
        self.title = title
        self.originalTitle = originalTitle
        self.movieIDURL = movieIDURL
        self.premiereDate = premiereDate
        self.city = city
        self.siteURL = siteURL
        self.nationalSiteURL = nationalSiteURL
        self.images = images
    }
}

// MARK: - Image
public struct Image: Decodable {
    public let url: String
    public let type: String

    public init(url: String, type: String) {
        self.url = url
        self.type = type
    }
}

// MARK: - PremiereDate
public struct PremiereDate: Decodable {
    public let localDate: Date
    public let isToday: Bool
    public let dayOfWeek, dayAndMonth, hour, year: String

    public init(localDate: Date, isToday: Bool, dayOfWeek: String, dayAndMonth: String, hour: String, year: String) {
        self.localDate = localDate
        self.isToday = isToday
        self.dayOfWeek = dayOfWeek
        self.dayAndMonth = dayAndMonth
        self.hour = hour
        self.year = year
    }
}

