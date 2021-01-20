package ohmypatt.patt.behavioral.command.foodorder.command;

import ohmypatt.patt.behavioral.command.foodorder.model.OrderList;

public interface OrderCommand {
	void execute(OrderList orderList);
}
