package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.layouts.BoxLayout;

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
            getToolbar().addCommandToSideMenu("Character Selection", null, e -> showTab("Character Selection"));
            getToolbar().addCommandToSideMenu("Character Status", null, e -> showTab("Character Status"));
            getToolbar().addCommandToSideMenu("Achievements", null, e -> showTab("Achievements"));
            getToolbar().addCommandToSideMenu("Settings", null, e -> showTab("Settings"));
            getToolbar().addCommandToSideMenu("Logout", null, e -> logout());
        }

        private void showTab(String tabName) {
            Dialog.show("Tab Selected", "You selected the " + tabName + " tab", "OK", null);
        }

        private void logout() {
            mainApp.showSignInForm();
        }
    }

    //Christy's section: Tasks

    //Andrea's section:  Achievements, Settings

    //Dawn's section: Characters 
}
