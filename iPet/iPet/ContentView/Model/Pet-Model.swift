//
//  Pet-Model.swift
//  iPet
//
//  Created by Andrea Chen on 2023/12/20.
//

import Foundation

struct Pet: Codable {
    var name: String
    var birthday = Date()
    var lastMeal: Date
    var lastDrink: Date
    
    var age: Int {
        let timeSince = calcTimeSince(data: birthday)
        return timeSince
    }
    
    var isAboutToDie: Bool {
        let timeThreshold: TimeInterval = 259200 // 3 days in seconds
        let timeSinceLastMeal = Date().timeIntervalSince(lastMeal)
        let timeSinceLastDrink = Date().timeIntervalSince(lastDrink)
        
        return timeSinceLastMeal >= timeThreshold && timeSinceLastDrink >= timeThreshold
    }
    
    var happinessLevel: String {
        hunger == "Hungry" || thirst == "Thirsty" ? "Unhappy" : "Happy"
    }
    
    var hunger: String {
        let timeSince = calcTimeSince(data: lastMeal)
        var string = ""
        
        switch timeSince {
        case 0..<30: string = "Satisfied"
        case 30..<60: string = "Getting hungry..."
        case 60...: string = "Hungry"
        case ...259200: string = "Dying"
        default: string = "IDK"
        }
        
        return string
    }
    
    var thirst: String {
        let timeSince = calcTimeSince(data: lastDrink)
        var string = ""
        
        switch timeSince {
        case 0..<30: string = "Satisfied"
        case 30..<60: string = "Getting thirsty..."
        case 60...: string = "Thirsty"
        case ...259200: string = "Dying"
        default: string = "IDK"
        }
        
        return string
    }
    
    
}
