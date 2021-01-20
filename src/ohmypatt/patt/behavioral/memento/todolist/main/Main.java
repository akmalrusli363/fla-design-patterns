package ohmypatt.patt.behavioral.memento.todolist.main;

import ohmypatt.patt.behavioral.memento.todolist.model.ToDo;
import ohmypatt.patt.behavioral.memento.todolist.model.TodoPad;

public class Main {
	public Main() {
		sampleCase();
	}
	
	private void sampleCase() {
		TodoPad myTasks = new TodoPad();
		myTasks.add(new ToDo("Nulis catatan"));
		System.out.println("Add task...");
		System.out.println(myTasks.getAll());
		
		myTasks.undo();
		System.out.println("Undo...");
		System.out.println(myTasks.getAll());
		
		myTasks.add(new ToDo("Ngoding PHP"));
		System.out.println("Add task...");
		System.out.println(myTasks.getAll());
		
		myTasks.add(new ToDo("Pesan boba"));
		myTasks.setDone(1, true);
		System.out.println("Set finished task...");
		System.out.println(myTasks.getAll());
		
		myTasks.undo();
		System.out.println("Undo...");
		System.out.println(myTasks.getAll());
		
		myTasks.redo();
		System.out.println("Redo...");
		System.out.println(myTasks.getAll());
	}

	public static void main(String[] args) {
		new Main();
	}

}
