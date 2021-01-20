package ohmypatt.patt.behavioral.memento.foodorder.model;

import java.util.Stack;

import ohmypatt.patt.behavioral.memento.foodorder.command.OrderCommand;

public class OrderCaretaker {
	private OrderList orderList;
	private Stack<OrderList.Memento> history;

	public OrderCaretaker(OrderList orderList) {
		this.orderList = orderList;
		history = new Stack<>();
	}
	
	public OrderCaretaker() {
		this(new OrderList());
	}
	
	public OrderList getOrderList() {
		return orderList;
	}
	
	public void executeCommand(OrderCommand command) {
		saveState();
		command.execute(orderList);
	}

	public void saveState() {
        history.push(orderList.save());
    }

	public void undo() {
		orderList.restore(history.pop());
	}
}