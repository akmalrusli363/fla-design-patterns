package ohmypatt.patt.behavioral.command.foodorder.model;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.command.foodorder.command.AddOrder;
import ohmypatt.patt.behavioral.command.foodorder.command.ExecuteOrder;
import ohmypatt.patt.behavioral.command.foodorder.command.OrderCommand;
import ohmypatt.patt.behavioral.command.foodorder.command.RemoveOrder;

public class Waiter {
	private OrderList orderList;
    
    public Waiter() {
        orderList = new OrderList();
    }
    
    public void addOrder(Order order) {
        execute(new AddOrder(order));
    }
    
    public void removeOrder(Order order) {
        execute(new RemoveOrder(order));
    }
    
    public void executeOrder() {
        execute(new ExecuteOrder());
    }
    
    public void executeBatch(ArrayList<OrderCommand> orderCommands) {
    	for (OrderCommand command : orderCommands) {
    		execute(command);
    	}
    }

	public void displayOrderedMenu() {
		System.out.println(orderList.toString());
	}

    private void execute(OrderCommand command) {
        command.execute(orderList);
    }
}
