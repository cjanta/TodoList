package com.java.todoListApp;


class App {

   public static void main(String[] args) {
        TodoList todoList = TodoList.getInstance();
        User user = new User("Hans");
        todoList.setUser(user);
    }   
    
}