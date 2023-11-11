package com.example.myapp;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Label;

public class MyApp extends com.codename1.system.Lifecycle {
    private Form signInForm;

    @Override
    public void runApp() {
        signInForm = new Form("Sign in", BoxLayout.y());

        TextComponent usernameCaption = new TextComponent();
        usernameCaption.label("username");
        //TextArea usernameSpace = new TextArea();

        TextComponentPassword passwordField = new TextComponentPassword();
        passwordField.label("password");
       // TextArea passwordSpace = new TextArea();

        signInForm.add(usernameCaption);
       // signInForm.add(usernameSpace);
        signInForm.add(passwordField);
       // signInForm.add(passwordSpace);

        Button signInButton = new Button("Sign in");
        signInForm.add(signInButton);
        signInButton.addActionListener(e -> signIn(usernameCaption.getText(), passwordField.getText()));

        signInForm.getToolbar().addMaterialCommandToSideMenu("Hello Command", FontImage.MATERIAL_CHECK, 4, e -> hello());
        signInForm.show();
    }

    private void signIn(String username, String password) {
        if (isValidCredentials(username, password)) {
            HomePage homePage = new HomePage(this);
            homePage.show();
        } else {
            Dialog.show("Invalid Credentials", "Please check your username and password", "OK", null);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void hello() {
        Dialog.show("Sign Up Complete!", "Do you confirm these updates", "Yes", "No");
    }

    public void showSignInForm() {
        signInForm.showBack();
    }

    public class HomePage extends Form {

        private final MyApp mainApp;

        public HomePage(MyApp mainApp) {
            super("Home Page", BoxLayout.y());
            this.mainApp = mainApp;

            getToolbar().addCommandToSideMenu("Tasks", null, e -> showTab("Tasks"));
            getToolbar().addCommandToSideMenu("Character Selection", null, e -> showCharacterSelection());
            getToolbar().addCommandToSideMenu("Character Selection", null, e -> showTab("Character Selection"));
            getToolbar().addCommandToSideMenu("Character Status", null, e -> showTab("Character Status"));
            getToolbar().addCommandToSideMenu("Achievements", null, e -> showTab("Achievements"));
            getToolbar().addCommandToSideMenu("Settings", null, e -> showTab("Settings"));
            getToolbar().addCommandToSideMenu("Logout", null, e -> logout());
        }

        private void showTab(String tabName) {
            if ("Settings".equals(tabName)) {
                showSettingsPage();
            } else {
                Dialog.show("Tab Selected", "You selected the " + tabName + " tab", "OK", null);
            }
        }

        private void logout() {
            mainApp.showSignInForm();
        }

        private void showCharacterSelection(){
            CharacterSelectionPage characterSelectionPage = new CharacterSelectionPage();
            characterSelectionPage.show(); 
        }

        private void showSettingsPage() {
            SettingsPage settingsPage = new SettingsPage();
            settingsPage.setPreviousForm(this); // Set the previous form to the current instance of HomePage
            settingsPage.show();
        }
    }

    public class CharacterSelectionPage extends Form {
        public CharacterSelectionPage(){
            super("Character Selection", BoxLayout.y());

            //add character selection page components and logic here
        }
    }

    public class SettingsPage extends Form {

        private Form previousForm;
    
        public SettingsPage() {
            super("Settings", BoxLayout.y());
    
            addButton("Account", () -> showSettingsPage(new AccountSettingsPage(this)));
            addButton("Privacy", () -> showSettingsPage(new PrivacySettingsPage(this)));
            addButton("Notification", () -> showSettingsPage(new NotificationSettingsPage(this)));
            addButton("Contact Support", () -> showSettingsPage(new ContactSupportPage(this)));
            addButton("Rate Us", () -> showSettingsPage(new RateUsPage(this)));
    
            // Add separation between buttons
            addAll(addSeparation(), getButtonsContainer(), addSeparation());
    
            // Add a back button to return to the previous form
            Button backButton = new Button("Back");
            backButton.addActionListener(e -> goToPreviousPage());
            add(backButton);
        }
    
        private void addButton(String label, Runnable action) {
            Button button = new Button(label);
            button.addActionListener(e -> action.run());
            add(button);
    
            // Debug statement
            System.out.println("Button added: " + label);
        }
    
        private Container addSeparation() {
            return BoxLayout.encloseY(new Label(), new Label()); // Add space between buttons
        }
    
        private Container getButtonsContainer() {
            return new Container(BoxLayout.y());
        }
    
        private void showSettingsPage(Form settingsPage) {
            settingsPage.show();
        }
    
        public void setPreviousForm(Form previousForm) {
            this.previousForm = previousForm;
        }
    
        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }
    
    // AccountSettingsPage
    public class AccountSettingsPage extends Form {

        private final Form previousForm;

        public AccountSettingsPage(Form previousForm) {
            super("Account Settings", BoxLayout.y());
            this.previousForm = previousForm;

            // Add account settings components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            contentContainer.add(new Label("Account Settings", "Title"));
            contentContainer.add(new SpanLabel("Account settings content goes here"));

            add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // PrivacySettingsPage
    public class PrivacySettingsPage extends Form {

        private final Form previousForm;

        public PrivacySettingsPage(Form previousForm) {
            super("Privacy Settings", BoxLayout.y());
            this.previousForm = previousForm;

            // Add privacy settings components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            contentContainer.add(new Label("Privacy Settings", "Title"));
            contentContainer.add(new SpanLabel("Privacy settings content goes here"));

            add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // NotificationSettingsPage
    public class NotificationSettingsPage extends Form {

        private final Form previousForm;

        public NotificationSettingsPage(Form previousForm) {
            super("Notification Settings", BoxLayout.y());
            this.previousForm = previousForm;

            // Add notification settings components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            contentContainer.add(new Label("Notification Settings", "Title"));
            contentContainer.add(new SpanLabel("Notification settings content goes here"));

            add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // ContactSupportPage
    public class ContactSupportPage extends Form {

        private final Form previousForm;

        public ContactSupportPage(Form previousForm) {
            super("Contact Support", BoxLayout.y());
            this.previousForm = previousForm;

            // Add contact support components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            contentContainer.add(new Label("Contact Support", "Title"));
            contentContainer.add(new SpanLabel("Contact support content goes here"));

            add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // RateUsPage
    public class RateUsPage extends Form {

        private final Form previousForm;

        public RateUsPage(Form previousForm) {
            super("Rate Us", BoxLayout.y());
            this.previousForm = previousForm;

            // Add rate us components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            contentContainer.add(new Label("Rate Us", "Title"));
            contentContainer.add(new SpanLabel("Rate us content goes here"));

            add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }
    //Christy's section: Tasks

    //Andrea's section:  Achievements, Settings

    //Dawn's section: Characters 
}
