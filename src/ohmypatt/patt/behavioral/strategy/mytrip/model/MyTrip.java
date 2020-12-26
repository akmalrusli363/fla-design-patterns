package ohmypatt.patt.behavioral.strategy.mytrip.model;

import ohmypatt.patt.behavioral.strategy.mytrip.strategy.ITravelMethod;

public class MyTrip {
  private ITravelMethod travelMethod;
  private Route route;

  public MyTrip(Route route, ITravelMethod travelMethod) {
    this.route = route;
    this.travelMethod = travelMethod;
  }

  public void setTravelMethod(ITravelMethod travelMethod) {
    this.travelMethod = travelMethod;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  public void performTrip() {
    travelMethod.performTrip(route);
  }
}