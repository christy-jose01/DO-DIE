package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
// import com.codename1.ui.List;
import com.codename1.ui.layouts.BoxLayout;
import com.example.myapp.CreateTaskPage;
import com.example.myapp.MyApp.CharacterSelectionPage;
import com.example.myapp.MyApp.CharacterStatusPage;
import com.example.myapp.MyApp.CustomProgressBar;
import com.example.myapp.MyApp.SettingsPage;
import com.example.myapp.MyApp.WeeklySummaryPage;
import com.example.myapp.Task;
import com.example.myapp.TaskManager;
import com.example.myapp.MyApp.CustomProgressBar;
import com.codename1.ui.CheckBox;
import java.util.ArrayList;
import java.util.List;

public class TasksOverviewPage extends Form{
    private final MyApp mainApp;
    private CustomProgressBar customProgressBar;
    private TaskManager taskManager;
    private List <Task> tasks;

    public TasksOverviewPage(MyApp mainApp, TaskManager taskManager){
        super("Tasks Overview", BoxLayout.y());
        this.mainApp = mainApp;
        if(taskManager == null){
            taskManager = TaskManager.getInstance();
        }
        this.taskManager = taskManager;
        this.tasks =  taskManager.getTasks();

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

        displayTasks();

        // Buttons
        // // go back
        // Button backButton = new Button("Back to Homepage");
        // backButton.addActionListener(e->mainApp.showHomePage());

        // add task
        Button addTaskButton = new Button("Add Task");
        addTaskButton.addActionListener(e->showCreateTaskPage());

        // adding to the Form
        add(addTaskButton);
        // add(backButton);
    }

    private void displayTasks(){
        // display tasks
        if(tasks.isEmpty()){
            // Sample label
            Label label = new Label("No Tasks");
            add(label);
        } else {
            Container taskContainer = new Container(BoxLayout.y());

            // Add CheckBox components for each task
            for (Task t : tasks) {
                CheckBox checkBox = new CheckBox(t.getTaskName());
                checkBox.setSelected(t.isDone());
                taskContainer.add(checkBox);

                checkBox.addActionListener(e-> {if(checkBox.isSelected()){
                    taskManager.markTaskDone(t);
                    
                    // taskContainer.removeComponent(checkBox);
                    // tasks.remove(t);
                    mainApp.showTaskOverview();
                }});
            }
            add(taskContainer);
        }
        
    }

    private void showCreateTaskPage(){
        CreateTaskPage createTaskPage = new CreateTaskPage(mainApp, taskManager);
        createTaskPage.show();
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





    public void updateProgressBar(float progress) {
        customProgressBar.setProgress(progress);
    }
    
    // Method to update the progress bar value
    public void setCustomProgressBarValue(float value) {
        if (customProgressBar != null) {
            customProgressBar.setProgress(value);
        }
    }
}
