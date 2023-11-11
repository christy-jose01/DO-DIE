package com.example.myapp;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.PickerComponent;

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

    public void showTaskOverviewPage(){
        TaskOverviewPage tovp = new TaskOverviewPage(this);
        tovp.show();
    }

    public class HomePage extends Form {

        private final MyApp mainApp;

        public HomePage(MyApp mainApp) {
            super("Home Page", BoxLayout.y());
            this.mainApp = mainApp;

            getToolbar().addCommandToSideMenu("Tasks", null, e -> showTaskOverviewPage());
            getToolbar().addCommandToSideMenu("Tasks", null, e -> showTab("Tasks"));
            getToolbar().addCommandToSideMenu("Character Selection", null, e -> showCharacterSelection());
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

        private void showCharacterSelection(){
            CharacterSelectionPage characterSelectionPage = new CharacterSelectionPage();
            characterSelectionPage.show();
        }

        private void showTaskOverviewPage() {
            TaskOverviewPage taskOverviewPage = new TaskOverviewPage(mainApp);
            taskOverviewPage.show();
        }
    }

    public class CharacterSelectionPage extends Form {
        public CharacterSelectionPage(){
            super("Character Selection", BoxLayout.y());

            //add character selection page components and logic here
        }
    }

    //Christy's section: Tasks
    public class TaskOverviewPage extends Form {
        // public Form TOverview;
        private final MyApp OverviewApp;

        public TaskOverviewPage(MyApp OverviewApp) {
            super("Task Overview", BoxLayout.y());
            this.OverviewApp = OverviewApp;

            // Add components and logic for the task overview page here
            // For example, you can add labels, buttons, etc.
            
            // Sample label
            Label label = new Label("No Tasks");
            
            // Button
            Button addTaskButton = new Button("Add Task");
            addTaskButton.addActionListener(e->showCreateTaskPage());

            // adding to the Form
            add(label);
            add(addTaskButton);

            // Sample button
            // Button backButton = new Button("Back to Home");
            // backButton.addActionListener(e -> backToHome());
            // add(backButton);
        }

        private void showCreateTaskPage(){
            CreateTaskPage createTaskPage = new CreateTaskPage(OverviewApp);
            createTaskPage.show();
        }
    }

    public class CreateTaskPage extends Form {
        private final MyApp taskApp;
    
        public CreateTaskPage(MyApp taskApp){
            super("Create Your Task", BoxLayout.y());
            this.taskApp = taskApp;

            //add character selection page components and logic here
            TextComponent TaskName = new TextComponent().label("Task Name");
            
            int min = 0;
            // int hour = 2;
            PickerComponent DueDate = PickerComponent.createTime(min).label("Due Time");

            // back button
            Button backToOverviewButton = new Button("Back to Tasks");
            
            // HomePage hp = new HomePage(mainApp);
            backToOverviewButton.addActionListener(e ->showTaskOverviewPage());

            add(TaskName);
            add(DueDate);
            add(backToOverviewButton);
        }

        private void showTaskOverviewPage(){
            taskApp.showTaskOverviewPage();
        }


        
    }

    //Andrea's section:  Achievements, Settings

    //Dawn's section: Characters 
}
