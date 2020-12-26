package ohmypatt.patt.behavioral.strategy.mytrip.strategy;

import ohmypatt.patt.behavioral.strategy.mytrip.model.Route;

public interface ITravelMethod {
  void performTrip(Route route);
}