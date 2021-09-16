//
//  UINavigationController+Extension.swift
//  Ingresso-Challenge
//
//  Created by Yuri Strack on 06/09/21.
//

import UIKit

extension UINavigationController {
    
    // Remove back button text and change navigation to transparent in detail view
    open override func viewWillLayoutSubviews() {
        navigationBar.topItem?.backButtonDisplayMode = .minimal
        
        // Workaround to change the navigation bar to transparent in Movies Detail View
        // Only works in this specific projetc, not recommended solution.
        if self.viewControllers.count > 1 {
            navigationBar.setBackgroundImage(UIImage(), for: .default)
            navigationBar.shadowImage = UIImage()
        }
        else {
            navigationBar.setBackgroundImage(nil, for: .default)
            navigationBar.shadowImage = nil
        }
    }
}
