package ohmypatt.patt.behavioral.strategy.mytrip.model;
public class Route {
  private final double distanceInKilometer;
  private final String source, destination;

  public Route(String source, String destination, double distanceInKilometer) {
    this.source = source;
    this.destination = destination;
    this.distanceInKilometer = distanceInKilometer;
  }

  public double getDistance() {
    return distanceInKilometer;
  }

  @Override
  public String toString() {
    return String.format("from %s to %s", source, destination);
  }
}

