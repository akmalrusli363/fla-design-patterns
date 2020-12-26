package ohmypatt.patt.behavioral.templatemethod.warmindo.order;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.templatemethod.warmindo.model.Noodle;

public class Order {
  private ArrayList<Noodle> orders = new ArrayList<Noodle>();

  public void newOrder(Noodle noodle) {
    orders.add(noodle);
  }

  /**
   * Queue First-in-First-out
   */
  public void serve() {
    Noodle noodle = orders.get(0);
    System.out.println(">> Serving " + noodle.getName());
    noodle.makeNoodle();
    System.out.println();
    orders.remove(noodle);
  }
}