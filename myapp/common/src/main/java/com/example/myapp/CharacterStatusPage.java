package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.Timeline;
import com.codename1.ui.layouts.BoxLayout;
import com.example.myapp.MyApp.Pet;
import java.util.Timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import com.codename1.l10n.SimpleDateFormat;

public class CharacterStatusPage extends Form {
    private final MyApp mainApp;
    private Pet pet;

    private Label ageLabel;
    private Label healthLabel;
    private Label happinessLabel;

    public CharacterStatusPage(MyApp mainApp) {
        super("Character Status", BoxLayout.y());
        this.mainApp = mainApp;

        // Initialize the Pet
        pet = mainApp.new Pet(new Date(), new Date());

            // "Back" button with a single arrow icon
            Button backButton = new Button(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Back", 5));
            backButton.addActionListener(e -> mainApp.showTaskOverview());
            addComponent(backButton);

        // Example: Add a button to feed the pet
        Button feedButton = new Button("Feed");
        feedButton.addActionListener(e -> mainApp.showTaskOverview());
        addComponent(feedButton);

        // Labels to display pet information
        ageLabel = new Label("Age: ");
        addComponent(ageLabel);

        healthLabel = new Label("Health: ");
        addComponent(healthLabel);

        happinessLabel = new Label("Happiness: ");
        addComponent(happinessLabel);

        // Add other UI components or actions as needed
        // ...

        // Initialize the UI
        updateUI();

        // Set up a timer to update UI every second
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateUI();
            }
        }, 0, 1000); // Update every second
    }

    public void updateUI() {
        // Update UI elements based on pet's status
        int ageInSeconds = pet.getAge();
        String health = pet.getHunger(); // Assuming you have a getHealth() method in Pet class
        String happinessLevel = pet.getHappinessLevel();
    
        // Update UI components accordingly
        Display.getInstance().callSerially(() -> {
            ageLabel.setText("Age: " + ageInSeconds + " seconds");
            healthLabel.setText("Health: " + health);
            happinessLabel.setText("Happiness: " + happinessLevel);
                });
        }
    }
    //  Now, the CharacterStatusPage class uses a Label to display the character status. You can update the character status by calling the updateCharacterStatus method with the desired status string. This should simplify the representation of character status without the need for a progress bar. If you have any specific requirements or adjustments, feel free to let me know!


