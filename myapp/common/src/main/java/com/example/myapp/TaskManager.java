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

    public void removeTask(Task task){
        if(instance == null){
            throw new IllegalStateException("Task Manager is not initialized");
        }

        if(tasks == null){
            throw new NullPointerException("No tasks in the system");
        }

        if(tasks.contains(task)){
            tasks.remove(task);
        }
    }

    public void markTaskDone(Task task){
        if(tasks.contains(task)){
            if(!task.isDone()){
                task.markDone();
            }
        }
    }

    public void unmarkTaskDone(Task task){
        if(tasks.contains(task)){
            if(task.isDone()){
                task.unmarkDone();
            }
        }
    }

    public float portionDone(){
        int totalTasks = tasks.size();

        if(totalTasks == 0){
            return 0;
        }

        int doneTasks = 0;

        for(Task t : tasks){
            if(t.isDone()){
                doneTasks++;
            }
        }

        return (float) doneTasks/totalTasks;
    }

    // Add other methods as needed
}