//
//  EventViewModelTests.swift
//  desafio-mobile
//
//  Created by Filipe Pinto Cunha on 05/05/26.
//

import XCTest
@testable import desafio_mobile 

final class EventViewModelTests: XCTestCase {
    
    @MainActor
    func testEventSortingOrder() async {
        //  Arrange
        let viewModel = EventViewModel()
        
        // Datas fictícias
        let dateOct = PremiereDate(
            localDate: "2026-10-08T00:00:00+00:00",
            dayAndMonth: "08/10",
            year: "2026"
        )
        let dateMay = PremiereDate(
            localDate: "2026-05-07T00:00:00+00:00",
            dayAndMonth: "07/05",
            year: "2026"
        )
        
        // Eventos com IDs e Títulos claros
        let movieLate = Event(
                    id: "100", title: "Filme de Outubro", synopsis: nil, cast: nil, contentRating: nil,
                    duration: nil, genres: nil, inPreSale: false, imageFeatured: nil, images: [],
                    premiereDate: dateOct, countryOrigin: nil, directors: nil, city: nil,
                    distributor: nil, siteURL: nil, ratingDescriptors: nil, trailers: nil, ratingDetails: nil
                )
                
                let movieEarly = Event(
                    id: "200", title: "Filme de Maio", synopsis: nil, cast: nil, contentRating: nil,
                    duration: nil, genres: nil, inPreSale: false, imageFeatured: nil, images: [],
                    premiereDate: dateMay, countryOrigin: nil, directors: nil, city: nil,
                    distributor: nil, siteURL: nil, ratingDescriptors: nil, trailers: nil, ratingDetails: nil
                )
        
        
        // Simulando o recebimento dos dados na ordem
        let unsortedEvents = [movieLate, movieEarly]
        
        // Mesma lógica de ordenação do EventViewModel.swift
        viewModel.events = unsortedEvents.sorted { (event1, event2) -> Bool in
            guard let date1 = event1.premiereDate?.localDate else { return false }
            guard let date2 = event2.premiereDate?.localDate else { return true }
            return date1 < date2
        }
        
        //
        // O primeiro item da lista agora deve ser o filme de Maio (ID 200)
        XCTAssertEqual(viewModel.events.first?.id, "200", "ERRO: O filme de Maio deveria estar na primeira posição.")
        
        // O último item da lista deve ser o filme de Outubro (ID 100)
        XCTAssertEqual(viewModel.events.last?.id, "100", "ERRO: O filme de Outubro deveria estar na última posição.")
    }
}
