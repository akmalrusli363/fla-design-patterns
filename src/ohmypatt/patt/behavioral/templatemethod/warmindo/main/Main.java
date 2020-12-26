package ohmypatt.patt.behavioral.templatemethod.warmindo.main;

import ohmypatt.patt.behavioral.templatemethod.warmindo.model.ChickenNoodle;
import ohmypatt.patt.behavioral.templatemethod.warmindo.model.FriedNoodle;
import ohmypatt.patt.behavioral.templatemethod.warmindo.model.NoodleSoup;
import ohmypatt.patt.behavioral.templatemethod.warmindo.order.Order;

public class Main {
  public Main() {
    Order order = new Order();

    System.out.println(">> Placing 4 orders...");
    order.newOrder(new NoodleSoup());
    order.newOrder(new ChickenNoodle());
    order.newOrder(new NoodleSoup("Soto Mie"));
    order.newOrder(new FriedNoodle());

    System.out.println(">> Serving orders one by one...");
    order.serve();
    order.serve();
    order.serve();
    order.serve();
  }

  public static void main(String[] args) {
    new Main();
  }
}