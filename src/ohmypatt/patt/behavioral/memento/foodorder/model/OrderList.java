package ohmypatt.patt.behavioral.memento.foodorder.model;

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
    
    private Vector<Order> cloneOrder() {
    	return (Vector<Order>) orderList.clone();
    }

    public Memento save() {
        return new Memento(cloneOrder());
    }

    public void restore(Memento m) {
        orderList = m.orderState;
    }

    public static class Memento {
        private Vector<Order> orderState;

        public Memento(Vector<Order> orderState) {
            this.orderState = orderState;
        }

        public Vector<Order> getOrderState() {
            return orderState;
        }
    }
}