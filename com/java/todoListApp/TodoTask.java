package com.java.todoListApp;

public class TodoTask {

    public String creatorName;
    public String titel;
    public String description;
    public String category;
    public String isDoneString;

    public TodoTask(String creatorName, String titel, String description, String category, String isDoneString) {
        this.creatorName = creatorName;
        this.titel = titel;
        this.description = description;
        this.category = category;
        this.isDoneString = isDoneString;
    } 

}
