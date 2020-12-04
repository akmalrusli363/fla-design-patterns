package ohmypatt.patt.structural.proxy.berryservice.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.berryservice.model.Order;
import ohmypatt.patt.structural.proxy.berryservice.model.Product;

public class ProxyBerryService {
  private Order order;
  private ArrayList<Order> orderList;
  private RealBerryService service;

  /**
   * Declare Proxy dengan inisialisasi Real Service
   */
  public ProxyBerryService() {
    service = new RealBerryService();
  }

  /**
   * Validasikan order yang akan diinput sebelum masuk ke service
   */
  public void placeOrder(Product product, int quantity) {
    if (product != null && quantity > 0) {
      service.placeOrder(product, quantity);
    }
  }

  /**
   * Ambil semua order bila belum di-cache atau gunakan order yang ada
   */
  public ArrayList<Order> getAllOrders() {
    if (orderList == null) {
      orderList = service.getAllOrders();
    }
    return orderList;
  }

  /**
   * Overload method untuk memperbolehkan Client untuk mengambil
   * data paling baru dari server
   */
  public ArrayList<Order> getAllOrders(boolean force) {
    orderList = force ? service.getAllOrders() : getAllOrders();
    return orderList;
  }

  /**
   * Validasikan order yang akan diinput sebelum masuk ke service
   */
  public Order getOrderByID(int id) {
    if (order == null || order.getOrderId() != id) {
      order = service.getOrderByID(id);
    }
    return order;
  }
}