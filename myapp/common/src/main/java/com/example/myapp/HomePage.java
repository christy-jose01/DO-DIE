package com.example.myapp;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.example.myapp.MyApp.CharacterSelectionPage;
import com.example.myapp.MyApp.CharacterStatusPage;
import com.example.myapp.MyApp.CustomProgressBar;
import com.example.myapp.MyApp.SettingsPage;
import com.example.myapp.MyApp.WeeklySummaryPage;

public class HomePage extends Form {

    private final MyApp mainApp;
    private CustomProgressBar customProgressBar;

    public HomePage(MyApp mainApp) {
        super("Home Page", BoxLayout.y());
        this.mainApp = mainApp;

        // Create an icon
        Image homeIcon = FontImage.createMaterial(FontImage.MATERIAL_HOME, new Style());
        Label homeIconLabel = new Label(homeIcon);
    
        // Add the icon to the HomePage
        this.add(homeIconLabel);

        getToolbar().addCommandToSideMenu("Tasks", null, e -> mainApp.showTaskOverview());
        getToolbar().addCommandToSideMenu("Character Selection", null, e -> showCharacterSelection());
        getToolbar().addCommandToSideMenu("Character Status", null, e -> showCharacterStatus());
        getToolbar().addCommandToSideMenu("Achievements", null, e -> showTab("Achievements"));
        getToolbar().addCommandToSideMenu("Weekly Summary", null, e -> showWeeklySummary());
        getToolbar().addCommandToSideMenu("Settings", null, e -> showTab("Settings"));
        getToolbar().addCommandToSideMenu("Logout", null, e -> logout());


        // Add the custom progress bar at the bottom
        customProgressBar = new CustomProgressBar();
        customProgressBar.setProgress(0.75f); // Set an initial progress value (change as needed)
        add(BorderLayout.south(customProgressBar));

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
        labelStyle.setBgColor(0xe89091); // Green background color
        labelStyle.setBgTransparency(255); // Opaque background

        // Create a container with a centered layout for the icon
        Container centerContainer = new Container(new FlowLayout(Component.CENTER));
        centerContainer.add(PetIconLabel);

        // Add the container with the icon to the HomePage
        // Create a spacer to position the icon lower
        Label spacer = new Label();
        spacer.setPreferredH(Display.getInstance().convertToPixels(5)); // Adjust the height as needed

        // Add the spacer and the container to the form
        this.add(spacer);
        this.add(centerContainer);

    }


    public void updateProgressBar(float progress) {
        customProgressBar.setProgress(progress);
    }
    
    // Method to update the progress bar value
    public void setCustomProgressBarValue(float value) {
        if (customProgressBar != null) {
            customProgressBar.setProgress(value);
        }
    }

    private void showSettingsPage() {
        SettingsPage settingsPage = mainApp.new SettingsPage();
        settingsPage.setPreviousForm(this); // Set the previous form to the current instance of HomePage
        
        
        settingsPage.show();
    }

    private void showTab(String tabName) {
        if ("Settings".equals(tabName)) {
            showSettingsPage();
        } else {
            Dialog.show("Tab Selected", "You selected the " + tabName + " tab", "OK", null);
        }
    }
    
    private void showWeeklySummary() {
        WeeklySummaryPage weeklySummaryPage = mainApp.new WeeklySummaryPage(this);
        weeklySummaryPage.show();
    }

    private void logout() {
        mainApp.showSignInForm();
    }

    private void showCharacterStatus() {
        CharacterStatusPage characterStatusPage = mainApp.new CharacterStatusPage(mainApp);
        characterStatusPage.show();
    }

    private void showCharacterSelection(){
        CharacterSelectionPage charSelectPage = mainApp.new CharacterSelectionPage(mainApp);
        charSelectPage.show();
    }
    
}
