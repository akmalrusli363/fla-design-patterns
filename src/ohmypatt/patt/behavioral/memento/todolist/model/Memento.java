package ohmypatt.patt.behavioral.memento.todolist.model;

import java.util.ArrayList;

public class Memento {
    private ArrayList<ToDo> todoState;

    public Memento(ArrayList<ToDo> todoState) {
        this.todoState = todoState;
    }

    public ArrayList<ToDo> getState() {
        return todoState;
    }
}