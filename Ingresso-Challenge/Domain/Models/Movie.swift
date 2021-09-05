//
//  Movie.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 04/09/21.
//

import Foundation

struct Movie: Identifiable {
    var id: String
    var title: String
    var originalTitle: String
    var imagesURL: (portrait: String?, landscape: String?)
    var synopsis: String
    var duration: String
    var cast: String
    var director: String
    var distributor: String
    var countryOrigin: String
    var contentRating: String
    var premiereDate: String?
    
    init(from item: Item) {
        self.id = item.id
        self.title = item.title
        self.originalTitle = item.originalTitle
        self.imagesURL = (portrait: item.images.first?.url, landscape: item.images.last?.url)
        self.synopsis = item.synopsis
        self.duration = item.duration
        self.cast = item.cast
        self.director = item.director
        self.distributor = item.distributor
        self.countryOrigin = item.countryOrigin
        self.contentRating = item.contentRating
        
        // Transform string to date
        let isoDateFormatter = ISO8601DateFormatter()
        guard let dateString = item.premiereDate?.localDate, let date = isoDateFormatter.date(from: dateString) else {return}
        
        // Format date to display format
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd/MM/YY"
        
        // Set value
        self.premiereDate = dateFormatter.string(from: date)
    }
}
