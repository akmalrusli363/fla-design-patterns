package ohmypatt.patt.behavioral.memento.foodorder.command;

import ohmypatt.patt.behavioral.memento.foodorder.model.OrderList;

public interface OrderCommand {
	void execute(OrderList orderList);
}
