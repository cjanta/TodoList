package com.java.todoListApp;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TodoListFrame {

    @SuppressWarnings("unused")
    private JFrame todoListFrame;
    
    private TodoList todoList;
    private JTable table;

    private DefaultTableModel tableModel;

    public TodoListFrame(TodoList todoList){
        this.todoList = todoList;
        todoListFrame = getNewFrame();
    }

    public void update(ArrayList<TodoTask> tasks){
        tableModel.setRowCount(0);
        for (TodoTask task : tasks) {
            tableModel.addRow(new Object[]{task.creatorName, task.titel, task.description, task.category,task.isDoneString});         
        }

    }
       

     private JFrame getNewFrame(){
        
        JFrame frame = new JFrame("TODO List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
       
        frame.add(getTableInScrollPane(), BorderLayout.CENTER);
        frame.add(getButtoPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);

        return frame;
    }

    private JScrollPane getTableInScrollPane(){
        String[] columnNames = {"Erstellt von", "Titel", "Beschreibung", "Kategorie", "Erledigt"};
        Object[][] data = new Object[0][0];
        tableModel = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                
                return column != 0 && column != columnNames.length-1 && TodoList.getInstance().isTaskEditable(row) ;
            }
        };

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if(column > -1){
                    String newValue = tableModel.getValueAt(row, column).toString();

                    TodoList tList = TodoList.getInstance();
                    if (column == 1){
                        tList.SetTaskTitle(row, newValue);
                    }
                    else if (column == 2){
                        tList.SetTaskDescription(row, newValue);
                    }
                    else if (column == 3){
                        tList.SetTaskCategory(row, newValue);
                    }
                }

            }
        });
         
        this.table = new JTable(tableModel);    
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private JPanel getButtoPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));  // 1 row, 4 columns

        JButton addButton = new JButton("Aufgabe ersetellen");
        
        addButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = todoList.getUser() != null ? todoList.getUser().getUserName() : "Default";
                todoList.addTask(new TodoTask(username, "DefaultTitel", "Defaultbeschreibung", "DefaultKategorie", TodoList.TASK_TODO));
            }
            
        });
        
        JButton editButton = new JButton("Keine Funktion");

        JButton deleteButton = new JButton("Aufgabe löschen");
        deleteButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() > -1){
                    todoList.removeTask(table.getSelectedRow());
                }
            }
            
        });

        JButton endTaskButton = new JButton("Aufgabe beenden");
        endTaskButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() > -1){
                    todoList.endTask(table.getSelectedRow());
                }
            }
            
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(endTaskButton);

        return buttonPanel;
    }
}
