package ohmypatt.patt.structural.proxy.berryservice.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.berryservice.model.Order;
import ohmypatt.patt.structural.proxy.berryservice.model.Product;

/**
 * This class doesn't contain database connection as displayed in markdown documentation.
 * Instead, I use only ArrayList<> to demonstrate ability to access and input data.
 * 
 * @author akmalrusli363
 */
public class RealBerryService {
  private ArrayList<Order> orderList;

  public RealBerryService() {
	  orderList = new ArrayList<Order>();
  }

  public void placeOrder(Product product, int quantity) {
    Order order = new Order(product, quantity);
    orderList.add(order);
  }

  public ArrayList<Order> getAllOrders() {
    return orderList;
  }

  public Order getOrderByID(int id) {
    for (Order order : orderList) {
      if (order.getOrderId() == id) {
        return order;
      }
    } return null;
  }
}