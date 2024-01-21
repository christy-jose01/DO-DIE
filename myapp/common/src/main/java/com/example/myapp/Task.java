package com.example.myapp;

public class Task {
    private String taskname;
    private int duetime;
    
    public Task(String taskname, int duetime){
        this.taskname = taskname;
        this.duetime = duetime;
    }

    public String getTaskName(){
        return taskname;
    }

    public void setTaskName(String taskName) {
        this.taskname = taskName;
    }

    public int getDueDate() {
        return duetime;
    }

    public void setDueDate(int dueDate) {
        this.duetime = dueDate;
    }
}
