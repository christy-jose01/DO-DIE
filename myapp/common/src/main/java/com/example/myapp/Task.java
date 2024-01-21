package com.example.myapp;

public class Task {
    private String taskname;
    private int duetime;
    private boolean isDone;
    
    public Task(String taskname, int duetime){
        this.taskname = taskname;
        this.duetime = duetime;
        this.isDone = false;
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

    public boolean isDone(){
        return this.isDone;
    }

    public void markDone(){
        this.isDone = true;
    }

    public void unmarkDone(){
        this.isDone = false;
    }
}
