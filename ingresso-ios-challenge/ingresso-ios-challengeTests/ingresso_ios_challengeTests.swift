//
//  ingresso_ios_challengeTests.swift
//  ingresso-ios-challengeTests
//
//  Created by Phil on 26/08/21.
//

import XCTest
@testable import ingresso_ios_challenge
import Combine



class ingresso_ios_challengeTests: XCTestCase {
    func test_whenReceiveJson_Decode(){
        
        let sut = CatalogJson().json
        let jsonObj = convertStringToJson(sut)
        
        func convertStringToJson(_ json: String) -> Data {
                    
            if let jsonData = try? JSONEncoder().encode(json) {
                    return jsonData
            }
            return Data()
        }
        
        do{
        let decoder = JSONDecoder()
        decoder.dateDecodingStrategy = .iso8601
        
        let catalog = try decoder.decode(Catalog.self, from: jsonObj)
        
            XCTAssertEqual(catalog.items[0].title, "A Mensageira")
            
        }catch{
           print(error)
        }
        
    }

}







struct CatalogJson {
    var json : String{
        """
        {
            "items": [
                {
                    "id": "24135",
                    "title": "A Mensageira",
                    "originalTitle": "The Courier",
                    "movieIdUrl": "",
                    "ancineId": "E2000489400000",
                    "countryOrigin": "Estados Unidos",
                    "priority": 36,
                    "contentRating": "16 anos",
                    "duration": "99",
                    "rating": 0.0,
                    "synopsis": "Uma força-tarefa conjunta está protegendo uma testemunha chamada Nick Murch, que testemunhará contra um poderoso senhor do crime; Ezekiel Mannings. Na casa secreta, uma mensageira toca a campainha para entregar um pacote, que então se revela como algo que vai prejudicar o julgamento, deixando a culpa aos pés da mensageira. Quando alguém tenta matar a testemunha e a mensageira durante a entrega do pacote, ela então se dedica a proteger Nick, para garantir que ele termine o que foi planejado para fazer.\n\n",
                    "cast": "Olga Kurylenko, Gary Oldman, Amit Shah",
                    "director": "Zackary Adler",
                    "distributor": "PlayArte",
                    "inPreSale": false,
                    "isReexhibition": false,
                    "urlKey": "a-mensageira",
                    "isPlaying": false,
                    "countIsPlaying": 0,
                    "premiereDate": {
                        "localDate": "2021-09-09T00:00:00+00:00",
                        "isToday": false,
                        "dayOfWeek": "quinta-feira",
                        "dayAndMonth": "09/09",
                        "hour": "00:00",
                        "year": "2021"
                    },
                    "creationDate": "0001-01-01T00:00:00Z",
                    "city": "Rio de Janeiro",
                    "siteURL": "https://www.ingresso.com/rio-de-janeiro/desafio/filmes/a-mensageira?ing_source=api&ing_medium=link-filme&ing_campaign=desafio&ing_content=",
                    "nationalSiteURL": "https://www.ingresso.com/filme/a-mensageira?city=rio-de-janeiro&partnership=desafio?ing_source=api&ing_medium=link-filme&ing_campaign=desafio&ing_content=",
                    "images": [
                        {
                            "url": "https://ingresso-a.akamaihd.net/img/cinema/cartaz/24135-cartaz.jpg",
                            "type": "PosterPortrait"
                        },
                        {
                            "url": "https://ingresso-a.akamaihd.net/img/cinema/cartaz/24135-destaque.jpg",
                            "type": "PosterHorizontal"
                        }
                    ],
                    "genres": [
                        "Ação"
                    ],
                    "ratingDescriptors": [
                        "Drogas Lícitas",
                        "Linguagem Imprópria",
                        "Violência"
                    ],
                    "completeTags": [],
                    "tags": [],
                    "trailers": [
                        {
                            "type": "Youtube",
                            "url": "https://www.youtube.com/watch?v=jBqdhbWgkXc",
                            "embeddedUrl": "//www.youtube.com/embed/jBqdhbWgkXc"
                        }
                    ],
                    "boxOfficeId": null,
                    "partnershipType": null,
                    "rottenTomatoe": {
                        "id": "98e5515a-93b0-3f6e-a28b-e2206d4ebe38",
                        "criticsRating": "Rotten",
                        "criticsScore": "5",
                        "audienceRating": "Spilled",
                        "audienceScore": "26",
                        "originalUrl": "https://www.rottentomatoes.com/m/the_courier_2019"
                    }
                }
            ],
            "count": 1
        }
        """
        
        }
    }


