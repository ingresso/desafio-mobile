//
//  ingresso_ios_challengeTests.swift
//  ingresso-ios-challengeTests
//
//  Created by Phil on 26/08/21.
//

import XCTest
@testable import ingresso_ios_challenge

class ingresso_ios_challengeTests: XCTestCase {


    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        self.measure {
            CatalogViewModel().fetchCatalogs()
        }
    }

}
