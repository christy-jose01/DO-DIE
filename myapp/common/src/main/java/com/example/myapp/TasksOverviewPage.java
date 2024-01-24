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
// import com.example.myapp.MyApp.CustomProgressBar;
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
        getToolbar().addCommandToSideMenu("Character Selection", null, e -> mainApp.showCharacterSelection());
        getToolbar().addCommandToSideMenu("Character Status", null, e -> mainApp.showCharacterStatus());
        getToolbar().addCommandToSideMenu("Achievements", null, e -> showTab("Achievements"));
        getToolbar().addCommandToSideMenu("Weekly Summary", null, e -> showWeeklySummary());
        getToolbar().addCommandToSideMenu("Settings", null, e -> showTab("Settings"));
        getToolbar().addCommandToSideMenu("Logout", null, e -> logout());

        // Add the custom progress bar at the bottom
        customProgressBar = new CustomProgressBar();
        customProgressBar.setProgress(taskManager.portionDone()); // Set an initial progress value (change as needed)
        add(BorderLayout.south(customProgressBar));

        displayTasks();

        // Buttons
        // // go back
        // Button backButton = new Button("Back to Homepage");
        // backButton.addActionListener(e->mainApp.showHomePage());

        // add task
        Button addTaskButton = new Button("Add Task");
        addTaskButton.addActionListener(e->showCreateTaskPage());

        // // clear button
        // Button clearButton = new Button("Clear Completed Tasks");
        // clearButton.addActionListener(e->clearTasks());

        // adding to the Form
        add(addTaskButton);
        // add(clearButton);
    }


    // private void clearTasks() {
    //     if (!tasks.isEmpty()) {
    //         List<Task> tasksToRemove = new ArrayList<>(); // Create a new list to store the tasks to remove
    //         for (Task t : tasks) {
    //             if (t.isDone()) {
    //                 tasksToRemove.add(t); // Add the task to the list if it is done
    //             }
    //         }
    //         for (Task t : tasksToRemove) {
    //             // remove task
    //             taskManager.removeTask(t);
    
    //             Container taskContainer = (Container) getComponentAt(tasks.indexOf(t) + 1); // get the corresponding container
    //             CheckBox checkBox = (CheckBox) taskContainer.getComponentAt(0); // get the corresponding checkbox
    //             // remove container
    //             taskContainer.removeComponent(checkBox); // remove the checkbox
    //             // tasks.remove(t); // remove the task from the list (not necessary)
    //         }
    //         tasks.removeAll(tasksToRemove); // Remove the tasks from the list
    //         mainApp.showTaskOverview();
    //     }
    // }

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
                    customProgressBar.setProgress(taskManager.portionDone());
                    // taskContainer.removeComponent(checkBox);
                    // tasks.remove(t);
                    // mainApp.showTaskOverview();
                    this.show();
                } else{
                    taskManager.unmarkTaskDone(t);
                    customProgressBar.setProgress(taskManager.portionDone());
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
