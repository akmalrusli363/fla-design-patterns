package ohmypatt.patt.behavioral.memento.todolist.model;

import java.util.ArrayList;

public class ToDoList implements Cloneable {
    private ArrayList<ToDo> todoList;

    public ToDoList() {
        this.todoList = new ArrayList<>();
    }

    public ToDoList(ArrayList<ToDo> todoList) {
        this.todoList = todoList;
    }

    public void add(ToDo todo) {
        todoList.add(todo);
    }

    public ToDo get(int index) {
        return todoList.get(index);
    }

    public void update(int index, ToDo todo) {
        todoList.set(index, todo);
    }

    public void remove(int index) {
        todoList.remove(index);
    }

    public String getAll() {
        String output = "";
        if (todoList == null || todoList.isEmpty()) {
        	return " No todo entries...\n";
        }
        for (ToDo todo : todoList) {
            char done = (todo.isDone()) ? 'v' : ' ';
            output += String.format("(%c) %s\n", done, todo.getContent());
        } return output;
    }

    public Memento save() {
        return new Memento(copyTodoItems());
    }

    public void restore(Memento m) {
        todoList = m.getState();
    }
    
    @Override
    public ToDoList clone() {
    	return new ToDoList(copyTodoItems());
    }
    
    private ArrayList<ToDo> copyTodoItems() {
    	ArrayList<ToDo> copy = new ArrayList<>();
    	for (ToDo todo : todoList) {
    		copy.add(todo.clone());
    	} return copy;
    }
}