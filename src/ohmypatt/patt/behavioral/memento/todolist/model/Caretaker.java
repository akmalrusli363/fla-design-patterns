package ohmypatt.patt.behavioral.memento.todolist.model;

import java.util.ArrayList;

public class Caretaker {
    private ArrayList<Memento> history = new ArrayList<>();
    private int index = 0;

    public void addMemento(Memento memento) {
        this.removeMementoOnUpdate();
        history.add(memento);
        index = history.size()-1;
    }

    private void removeMementoOnUpdate() {
        int size = history.size();
        for (int i = index + 1; i < size; i++) {
            history.remove(i);
        }
        history.trimToSize();
    }

    public Memento undo() {
        index = (index > 0) ? index - 1 : 0;
        return history.get(index);
    }

    public Memento redo() {
        int maxIndex = history.size() - 1;
        index = (index < maxIndex) ? index + 1 : maxIndex;
        return history.get(index);
    }
}