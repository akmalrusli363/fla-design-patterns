package ohmypatt.patt.behavioral.memento.todolist.model;
public class TodoPad {
    private ToDoList todoList;
    private Caretaker caretaker;

    public TodoPad() {
        this(new ToDoList());
    }

    public TodoPad(ToDoList todoList) {
        this.todoList = todoList;
        caretaker = new Caretaker();
        saveState();
    }

    public void add(ToDo todo) {
        todoList.add(todo);
        saveState();
    }

    public ToDo get(int index) {
        return todoList.get(index);
    }

    public String getAll() {
        return todoList.getAll();
    }

    public void setTodoContent(int index, String todoContent) {
        ToDo todo = get(index);
        todo.setContent(todoContent);
        todoList.update(index, todo);
        saveState();
    }

    public void setDone(int index, boolean done) {
        ToDo todo = get(index);
        todo.setDone(done);
        todoList.update(index, todo);
        saveState();
    }

    public void remove(int index) {
        todoList.remove(index);
        saveState();
    }

    public void saveState() {
        caretaker.addMemento(todoList.save());
    }

    public void undo() {
        todoList.restore(caretaker.undo());
    }

    public void redo() {
        todoList.restore(caretaker.redo());
    }
}