package ohmypatt.patt.behavioral.memento.foodorder.model;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.memento.foodorder.command.OrderCommand;
import ohmypatt.patt.behavioral.memento.foodorder.command.AddOrder;
import ohmypatt.patt.behavioral.memento.foodorder.command.ExecuteOrder;
import ohmypatt.patt.behavioral.memento.foodorder.command.RemoveOrder;

public class Waiter {
	private OrderCaretaker handler;

	public Waiter() {
		handler = new OrderCaretaker();
	}

	public void addOrder(Order order) {
		System.out.println("Add order...");
		handler.executeCommand(new AddOrder(order));
	}

	public void removeOrder(Order order) {
		System.out.println("Remove order...");
		handler.executeCommand(new RemoveOrder(order));
	}

	public void executeOrder() {
		System.out.println("Placing order to Chef...");
		handler.executeCommand(new ExecuteOrder());
	}
    
    public void executeBatch(ArrayList<OrderCommand> orderCommands) {
    	for (OrderCommand command : orderCommands) {
    		handler.executeCommand(command);
    	}
    }

	public void undo() {
		System.out.println("Undoing an order action...");
		handler.undo();
	}

	public void displayOrderedMenu() {
		System.out.println(handler.getOrderList().toString());
	}
}
