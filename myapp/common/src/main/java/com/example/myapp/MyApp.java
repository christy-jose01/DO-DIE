
package com.example.myapp;

import javax.swing.Box;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
//import com.codename1.ui.ProgressBar;
import com.codename1.ui.Graphics;

// Import for ProgressBar
//import com.codename1.ui.spinner.ProgressBar;

// Import for Label
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

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

    public void showHomePage() {
        HomePage homePage = new HomePage(this);
        homePage.show();
    }

    public void showTaskOverview(){
        TasksOverviewPage tasksOverviewPage = new TasksOverviewPage(this);
        tasksOverviewPage.show();
    }

    public class HomePage extends Form {

        private final MyApp mainApp;
        private CustomProgressBar customProgressBar;

        public HomePage(MyApp mainApp) {
            super("Home Page", BoxLayout.y());
            this.mainApp = mainApp;

            getToolbar().addCommandToSideMenu("Tasks", null, e -> showTaskOverview());
            getToolbar().addCommandToSideMenu("Character Selection", null, e -> showCharacterSelection());
          //  getToolbar().addCommandToSideMenu("Character Selection", null, e -> showTab("Character Selection"));
          //  getToolbar().addCommandToSideMenu("Character Status", null, e -> showTab("Character Status"));
            getToolbar().addCommandToSideMenu("Character Status", null, e -> showCharacterStatus());
            getToolbar().addCommandToSideMenu("Achievements", null, e -> showTab("Achievements"));
        //    getToolbar().addCommandToSideMenu("Character Selection", null, e -> showTab("Character Selection"));
            getToolbar().addCommandToSideMenu("Character Status", null, e -> showTab("Character Status"));
            getToolbar().addCommandToSideMenu("Weekly Summary", null, e -> showWeeklySummary());
            getToolbar().addCommandToSideMenu("Settings", null, e -> showTab("Settings"));
            getToolbar().addCommandToSideMenu("Logout", null, e -> logout());

            // Add the custom progress bar at the bottom
            customProgressBar = new CustomProgressBar();
            customProgressBar.setProgress(0.75f); // Set an initial progress value (change as needed)
            add(BorderLayout.south(customProgressBar));
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
            SettingsPage settingsPage = new SettingsPage();
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
            WeeklySummaryPage weeklySummaryPage = new WeeklySummaryPage(this);
            weeklySummaryPage.show();
        }

        private void logout() {
            mainApp.showSignInForm();
        }


        private void showCharacterSelection() {
            CharacterSelectionPage characterSelectionPage = new CharacterSelectionPage(mainApp);
            characterSelectionPage.show();
        }

        private void showCharacterStatus() {
            CharacterStatusPage characterStatusPage = new CharacterStatusPage(mainApp);
            characterStatusPage.show();
        }


        
        
    }

    public class TasksOverviewPage extends Form{
        private final MyApp mainApp;

        public TasksOverviewPage(MyApp mainApp){
            super("Tasks Overview", BoxLayout.y());
            this.mainApp = mainApp;

            // Sample label
            Label label = new Label("No Tasks");
            
            // Buttons
            // go back
            Button backButton = new Button("Back to Homepage");
            backButton.addActionListener(e->showHomePage());

            // add task
            Button addTaskButton = new Button("Add Task");
            addTaskButton.addActionListener(e->showCreateTaskPage());

            // adding to the Form
            add(label);
            add(addTaskButton);
            add(backButton);
        }

        private void showCreateTaskPage(){
            CreateTaskPage createTaskPage = new CreateTaskPage(mainApp);
            createTaskPage.show();
        }


    }

    public class CreateTaskPage extends Form {
        private final MyApp mainApp;
    
        public CreateTaskPage(MyApp mainApp){
            super("Create Your Task", BoxLayout.y());
            this.mainApp = mainApp;

            //add character selection page components and logic here
            TextComponent TaskName = new TextComponent().label("Task Name");
            
            int min = 0;
            // int hour = 2;
            PickerComponent DueDate = PickerComponent.createTime(min).label("Due Time");

            // back button
            Button backToOverviewButton = new Button("Back to Tasks");
            
            // HomePage hp = new HomePage(mainApp);
            backToOverviewButton.addActionListener(e ->showTaskOverview());

            add(TaskName);
            add(DueDate);
            add(backToOverviewButton);
        }

        private void showTaskOverview(){
            mainApp.showTaskOverview();
        }


        
    }
    
    public class CharacterSelectionPage extends Form {
        private final MyApp mainApp;

        public CharacterSelectionPage(MyApp mainApp) {
            super("Character Selection", BoxLayout.y());
            this.mainApp = mainApp;
    
            
            
            Button backButton = new Button(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Back", 5));
            backButton.addActionListener(e -> showHomePage());
            addComponent(backButton);
            
        }

        private void showHomePage() {
            mainApp.showHomePage();
        }
    

    }

    
    public class CharacterStatusPage extends Form {
        private final MyApp mainApp;
    
        public CharacterStatusPage(MyApp mainApp) {
            super("Character Status", BoxLayout.y());
            this.mainApp = mainApp;
    
    
            // "Back" button with a single arrow icon
            Button backButton = new Button(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Back", 5));
            backButton.addActionListener(e -> showHomePage());
            addComponent(backButton);

        }
    
        private void showHomePage() {
            mainApp.showHomePage();
        }
    
    }

    // Update the character's health
    

    
  //  Now, the CharacterStatusPage class uses a Label to display the character status. You can update the character status by calling the updateCharacterStatus method with the desired status string. This should simplify the representation of character status without the need for a progress bar. If you have any specific requirements or adjustments, feel free to let me know!
    public interface PreviousFormSetter {
        void setPreviousForm(Form previousForm);
    }
    
    public class SettingsPage extends Form implements PreviousFormSetter {

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
    
    
        @Override
    
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

    public class CustomProgressBar extends Container {

        private float progress;

        public CustomProgressBar() {
            this.progress = 0.5f; // Set the initial progress value
            getAllStyles().setBgColor(0xFFFFFF); // Set the background color
            getAllStyles().setBorder(Border.createLineBorder(1, 0x000000)); // Set the border color and size
        }

        public float getProgress() {
            return progress;
        }

        public void setProgress(float progress) {
            if (progress >= 0 && progress <= 1) {
                this.progress = progress;
                repaint(); // Trigger repaint to update the progress bar
            }
        }

        @Override
        protected void paintBackground(Graphics g) {
            super.paintBackground(g);

            // Calculate the width of the filled area based on the progress
            int fillWidth = (int) (getWidth() * progress);

            // Set the color of the filled area
            g.setColor(0x007AFF); // You can customize the color here

            // Draw the filled area
            g.fillRect(getX(), getY(), fillWidth, getHeight());
        }

        @Override
        protected Dimension calcPreferredSize() {
            return new Dimension(260, 37); // Set the preferred size of your progress bar
        }
    }

    public class WeeklySummaryPage extends Form {

        private final Form previousForm;
    
        public WeeklySummaryPage(Form previousForm) {
            super("Weekly Summary", BoxLayout.y());
            this.previousForm = previousForm;
    
            // Days of the week
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    
            for (String day : days) {
                // Day label
                Label dayLabel = new Label(day);
                dayLabel.getAllStyles().merge(createDayLabelStyle());
                add(dayLabel);
    
                // Custom progress bar
                CustomProgressBar customProgressBar = new CustomProgressBar();
                add(customProgressBar);
    
                // Spacing between days
                add(createSpacer());
            }
    
            // Set the back command to return to the previous form
            getToolbar().setBackCommand("Back", e -> goToPreviousPage());
        }
    
        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    
        private Style createDayLabelStyle() {
            Style dayLabelStyle = new Style();
            dayLabelStyle.setBgColor(0xFFFFFF);
            dayLabelStyle.setFgColor(0x000000);
            dayLabelStyle.setBorder(Border.createLineBorder(1, 0x000000));
            //dayLabelStyle.setFont(Font.createTrueTypeFont("Inter", "Inter-Regular.ttf", Font.STYLE_PLAIN, 20));
            //dayLabelStyle.setFont(Font.createTrueTypeFont("Inter", "Inter-Regular.ttf").derive(Font.STYLE_PLAIN, 20));
            dayLabelStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
    
            return dayLabelStyle;
        }
    
        private Label createSpacer() {
            Label spacer = new Label();
            spacer.getAllStyles().merge(createSpacerStyle());
            return spacer;
        }
    
        private Style createSpacerStyle() {
            Style spacerStyle = new Style();
            spacerStyle.setBgColor(0xFFFFFF);
            spacerStyle.setBgTransparency(255);
            return spacerStyle;
        }
        
    }
    
    
    
    
    //Christy's section: Tasks

    //Andrea's section:  Achievements, Settings

    //Dawn's section: Characters

}