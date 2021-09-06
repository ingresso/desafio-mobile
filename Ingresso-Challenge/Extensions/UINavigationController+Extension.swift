//
//  UINavigationController+Extension.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import UIKit

extension UINavigationController {
    // Remove back button text
    open override func viewWillLayoutSubviews() {
        navigationBar.topItem?.backButtonDisplayMode = .minimal
    }
}
