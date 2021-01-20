package ohmypatt.patt.behavioral.command.foodorder.command;

import ohmypatt.patt.behavioral.command.foodorder.model.Order;
import ohmypatt.patt.behavioral.command.foodorder.model.OrderList;

public class RemoveOrder implements OrderCommand {
	private Order order;

	public RemoveOrder(Order order) {
		this.order = order;
	}

    public void setOrder(Order order) {
        this.order = order;
    }

	@Override
	public void execute(OrderList orderList) {
        System.out.println("Remove order '" + order + "'...");
		orderList.removeOrder(order);
	}
}
