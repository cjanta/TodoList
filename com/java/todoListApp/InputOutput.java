package com.java.todoListApp;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class InputOutput {

    public String getPath(){
        String appFolderPath = getClass().getResource("").getPath();
        String fileName = "my_data.ser";
        String filePath = appFolderPath + fileName; // Adjust the separator for your operating system
        return filePath;
    }

    public void saveTodoTasksToFile(String fileName, ArrayList<TodoTask> taskList) {
        try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTodoTasksFromFile(String fileName, ArrayList<TodoTask> taskList) {

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            taskList = (ArrayList<TodoTask>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}




