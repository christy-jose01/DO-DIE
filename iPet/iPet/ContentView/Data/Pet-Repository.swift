//
//  Pet-Repository.swift
//  iPet
//
//  Created by Andrea Chen on 2023/12/20.
//

import Foundation

class PetRepository {
    private var PET_KEY = "PET_KEY"
    private var pet: Pet
    
    init() {
        if let data = UserDefaults.standard.data(forKey: PET_KEY) {
            if let decoded = try? JSONDecoder().decode(Pet.self, from: data) {
                self.pet = decoded
                print("Pet data successfully retrieved!")
                return
            }
        }
        self.pet = Pet(name: "Yoshi", lastMeal: Date(), lastDrink: Date())
    }
    
    func loadData() -> Pet {
        return self.pet
    }
    
    func saveData(pet: Pet) {
        if let encoded = try? JSONEncoder().encode(pet) {
            UserDefaults.standard.set(encoded, forKey: PET_KEY)
            print("Data saved at: \(Date().formatted(date: .omitted, time: .standard))")
        }
    }
    
    func clearData() {
        UserDefaults.standard.removeObject(forKey: PET_KEY)
        print("Data cleared at: \(Date().formatted(date: .omitted, time: .standard))")
    }
}
