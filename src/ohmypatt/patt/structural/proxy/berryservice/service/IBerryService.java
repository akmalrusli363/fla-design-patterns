package ohmypatt.patt.structural.proxy.berryservice.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.berryservice.model.Order;
import ohmypatt.patt.structural.proxy.berryservice.model.Product;

public interface IBerryService {
  void placeOrder(Product product, int quantity);
  ArrayList<Order> getAllOrders();
  Order getOrderByID(int id);
}