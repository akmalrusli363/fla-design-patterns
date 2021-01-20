package ohmypatt.patt.behavioral.memento.foodorder.command;

import ohmypatt.patt.behavioral.memento.foodorder.model.Order;
import ohmypatt.patt.behavioral.memento.foodorder.model.OrderList;

public class RemoveOrder implements OrderCommand {
	private Order order;

	public RemoveOrder(Order order) {
		this.order = order;
	}

	@Override
	public void execute(OrderList orderList) {
		orderList.removeOrder(order);
	}
}
