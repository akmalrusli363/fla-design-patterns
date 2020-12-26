package ohmypatt.patt.behavioral.strategy.mytrip.strategy;

import ohmypatt.patt.behavioral.strategy.mytrip.model.Route;

public class Ojol implements ITravelMethod {
  private static final double minutePerKilometer = 1.2;

  private int estimateCost(double distance) {
    double extendedDistance = (distance < 4) ? 0 : (distance - 4);
    return (int) Math.round(10000 + (extendedDistance * 3000));
  }

  private double estimateTime(double distance) {
    return distance * minutePerKilometer;
  }

  @Override
  public void performTrip(Route route) {
    System.out.printf("Performing an ride %s using bike sharing...\n",
                        route.toString());
    System.out.printf("Time estimated: %.1f minutes\n",
                        estimateTime(route.getDistance()));
    System.out.printf("Cost: Rp%10d\n", estimateCost(route.getDistance()));
  }
}

