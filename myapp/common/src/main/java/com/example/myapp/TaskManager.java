package com.example.myapp;

import java.util.ArrayList;
import java.util.List;

// import com.codename1.ui.List;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;

    private TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (instance == null){
            instance = TaskManager.getInstance();
        }
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

    // Add other methods as needed
}