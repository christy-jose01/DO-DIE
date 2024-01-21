package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
// import com.codename1.ui.List;
import com.codename1.ui.layouts.BoxLayout;
import com.example.myapp.CreateTaskPage;
import com.example.myapp.Task;
import com.example.myapp.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class TasksOverviewPage extends Form{
    private final MyApp mainApp;
    private TaskManager taskManager;

    public TasksOverviewPage(MyApp mainApp, TaskManager taskManager){
        super("Tasks Overview", BoxLayout.y());
        this.mainApp = mainApp;
        if(taskManager == null){
            taskManager = TaskManager.getInstance();
        }
        this.taskManager = taskManager;

        List <Task> tasks = taskManager.getTasks();
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
                taskContainer.add(checkBox);
            }

            add(taskContainer);
        }
        
        
        // Buttons
        // go back
        Button backButton = new Button("Back to Homepage");
        backButton.addActionListener(e->mainApp.showHomePage());

        // add task
        Button addTaskButton = new Button("Add Task");
        addTaskButton.addActionListener(e->showCreateTaskPage());

        // adding to the Form
        add(addTaskButton);
        add(backButton);
    }

    private void showCreateTaskPage(){
        CreateTaskPage createTaskPage = new CreateTaskPage(mainApp, taskManager);
        createTaskPage.show();
    }
}
