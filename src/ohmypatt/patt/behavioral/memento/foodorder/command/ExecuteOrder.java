package ohmypatt.patt.behavioral.memento.foodorder.command;

import ohmypatt.patt.behavioral.memento.foodorder.model.OrderList;

public class ExecuteOrder implements OrderCommand {
    @Override
    public void execute(OrderList orderList) {
        orderList.executeOrder();
    }
}