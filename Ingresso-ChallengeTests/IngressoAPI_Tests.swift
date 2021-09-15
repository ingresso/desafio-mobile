//
//  Ingresso_ChallengeTests.swift
//  Ingresso-ChallengeTests
//
//  Created by Yuri Strack on 15/09/21.
//

import XCTest
@testable import Ingresso_Challenge

class IngressoAPI_Tests: XCTestCase {

    func testFetchMovies() throws {
        let api = APIDataSource()
        
        let expectation = self.expectation(description: "Fetching")
        var response: APIResponse?
        
        api.getMovies(completion: { result in
            switch result {
            case .success(let _response):
                response = _response
                expectation.fulfill()
            case .failure(_):
                XCTFail("API error")
            }
        })
        
        waitForExpectations(timeout: 5, handler: nil)
        
        XCTAssertNotNil(response)
    }
}
