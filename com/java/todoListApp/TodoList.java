package com.java.todoListApp;


import java.util.*;

public class TodoList {
    
    private static TodoList instance;
    public static final String TASK_DONE = "Erledigt";
    public static final String TASK_TODO = "TODO";

    private ArrayList<TodoTask> tasks = new ArrayList<>();
    private TodoListFrame frameComponent = new TodoListFrame(this);
    private User user;

    private TodoList(){
        //hide
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static TodoList getInstance(){
        if(instance == null){
            instance = new TodoList();
        }

        return instance;
    }

    public void addTask(TodoTask task){
        tasks.add(task);
        updateFrameComponent();
    }

    public void removeTask(int index){
        tasks.remove(index);
        updateFrameComponent();
    }

    public void endTask(int index){
        tasks.get(index).isDoneString = TodoList.TASK_DONE;
        updateFrameComponent();
    }

    public boolean isTaskEditable(int index){
        return tasks.get(index).isDoneString == TodoList.TASK_TODO;
    }

    public void SetTaskTitle(int index, String newTitle){
        tasks.get(index).titel = newTitle;
    }

    public void SetTaskDescription(int index, String newDescription){
        tasks.get(index).description = newDescription;
    }

    public void SetTaskCategory(int index, String category){
        tasks.get(index).category = category;
    }

   private void updateFrameComponent(){
    frameComponent.update(tasks);
   }
}
