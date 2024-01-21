package com.example.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

public class CreateTaskPage extends Form {
    private final MyApp mainApp;
    private TaskManager taskManager;

    public CreateTaskPage(MyApp mainApp, TaskManager taskManager){
        super("Create Your Task", BoxLayout.y());
        this.mainApp = mainApp;
        if(taskManager == null){
            taskManager = TaskManager.getInstance();
        }
        this.taskManager = taskManager;

        //add character selection page components and logic here
        TextComponent TaskName = new TextComponent().label("Task Name");
        
        Picker Timepicker = new Picker();
        Timepicker.setType(Display.PICKER_TYPE_TIME);

        // back button
        Button backToOverviewButton = new Button("Back to Tasks");
        backToOverviewButton.addActionListener(e -> saveTask(TaskName.getText(), Timepicker.getTime()));
        // backToOverviewButton.addActionListener(e ->showTaskOverview());

        add(TaskName);
        add(Timepicker);
        add(backToOverviewButton);
    }

    private void showTaskOverview(){
        mainApp.showTaskOverview();
    }

    private void saveTask(String taskName, int dueDate) {
        if(!taskName.isEmpty()){
            Task newTask = new Task(taskName, dueDate);
            taskManager.addTask(newTask);
            showTaskOverview();
        }
    }
}
