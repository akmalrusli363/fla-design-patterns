package ohmypatt.patt.behavioral.command.foodorder.command;

import ohmypatt.patt.behavioral.command.foodorder.model.OrderList;

public class ExecuteOrder implements OrderCommand {
    @Override
    public void execute(OrderList orderList) {
        System.out.println("Placing order to Chef...");
        orderList.executeOrder();
    }
}