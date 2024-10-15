package com.java.todoListApp;


class App {

   public static void main(String[] args) {
        TodoList todoList = TodoList.getInstance();
        User user = new User("Hans");
        todoList.setUser(user);

        TodoTask tempTask = new TodoTask(user.getUserName(), "Erster Titel", "Beschreibung", "DEV", "Erledigt");
        todoList.addTask(tempTask);

        todoList.addTask(new TodoTask(user.getUserName(), "Zweiter Titel", "Beschreibung", "DEV", "TODO"));
        
    }

    

    
    
}