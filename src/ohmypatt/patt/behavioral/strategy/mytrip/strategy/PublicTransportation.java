package ohmypatt.patt.behavioral.strategy.mytrip.strategy;

import ohmypatt.patt.behavioral.strategy.mytrip.model.Route;

public class PublicTransportation implements ITravelMethod {
	private int estimateCost(double distance) {
		return (int) (3000 + (Math.round(distance / 10) * 1000));
	}

	private double estimateTime(double distance) {
		// always minimum 3 minute because need to stop at each station
		return distance * 3;
	}

	@Override
	public void performTrip(Route route) {
		System.out.printf("Performing an ride %s using public transportation...\n", route.toString());
		System.out.printf("Time estimated: %.1f minutes\n", estimateTime(route.getDistance()));
		System.out.printf("Cost: Rp%10d\n", estimateCost(route.getDistance()));
	}
}