package ohmypatt.patt.behavioral.command.foodorder.model;

import java.util.Vector;

public class OrderList {
    private Vector<Order> orderList;

    public OrderList() {
        orderList = new Vector<>();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    public void executeOrder() {
        for (Order order : orderList) {
            order.execute();
        } orderList.clear();
    }

    @Override
    public String toString() {
    	if (orderList.isEmpty()) {
    		return " >> no available orders!\n";
    	}
        String str = "";
        for (Order order : orderList) {
        	str += " - " + order.toString() + "\n";
        } return str;
    }
}