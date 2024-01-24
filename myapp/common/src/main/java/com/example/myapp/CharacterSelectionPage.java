package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;

// Character Selection Page
public class CharacterSelectionPage extends Form {
    private final MyApp mainApp;

    public CharacterSelectionPage(MyApp mainApp) {
        super("Character Selection", BoxLayout.y());
        this.mainApp = mainApp;


        // Create the style for the icon
        Style s = new Style();
        s.setFont(FontImage.getMaterialDesignFont().derive(Display.getInstance().convertToPixels(30), Font.STYLE_PLAIN));

        // Create the character icon
        s.setFgColor(0xffa0a9); // Set the foreground color to red (in ARGB format)
        Image PetIcon = FontImage.createMaterial(FontImage.MATERIAL_FACE, s);

        // Use the icon in a button
        Label PetIconLabel = new Label(PetIcon);
        // Set background color
        Style labelStyle = PetIconLabel.getAllStyles();
        labelStyle.setBgColor(0xe89091); 
        labelStyle.setBgTransparency(255);

        // Create a container with a centered layout for the icon
        Container centerContainer = new Container(new FlowLayout(Component.CENTER));
        centerContainer.add(PetIconLabel);

        // Add the container with the icon to the HomePage
        // Create a spacer to position the icon lower
        Label spacer = new Label();
        spacer.setPreferredH(Display.getInstance().convertToPixels(5)); 

        // Add the spacer and the container to the form
        this.add(spacer);
        this.add(centerContainer);

        Label spacer1 = new Label();
        spacer1.setPreferredH(Display.getInstance().convertToPixels(10)); 

        // Add the spacer and the container to the form
        this.add(spacer1);

        
        
        Button backButton = new Button(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Back", 5));
        backButton.addActionListener(e -> mainApp.showTaskOverview());
        addComponent(backButton);
        
    }



}