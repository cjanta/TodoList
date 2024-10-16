package com.java.todoListApp;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class InputOutput {

    private static final String FILENAME = "Tasks.todo";


    public static void saveTodoTasksToFile() {
        try (FileOutputStream fos = new FileOutputStream(FILENAME); ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(TodoList.getInstance().getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadTodoTasksFromFile() {
        
        ArrayList<TodoTask> loadedTaskList;
        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                loadedTaskList = (ArrayList<TodoTask>) ois.readObject();
                TodoList.getInstance().setTasks(loadedTaskList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}




