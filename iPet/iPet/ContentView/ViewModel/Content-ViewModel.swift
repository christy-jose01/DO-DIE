//
//  Content-ViewModel.swift
//  iPet
//
//  Created by Andrea Chen on 2023/12/20.
//

import Foundation
import SwiftUI

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var pet: Pet
        private var repository = PetRepository()
        
        init() {
            pet = repository.loadData()
        }
        
        func saveData() {
            objectWillChange.send()
            repository.saveData(pet: pet)
        }
        
        func clearData() {
            objectWillChange.send()
            repository.clearData()
        }
        func feed() {
            pet.lastMeal = Date()
            saveData()
        }
        
        func giveWater() {
            pet.lastDrink = Date()
            saveData()
        }
        
        func restart() {
            clearData()
        }
    }
}
