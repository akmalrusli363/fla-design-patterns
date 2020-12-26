package ohmypatt.patt.behavioral.strategy.mytrip.strategy;

import ohmypatt.patt.behavioral.strategy.mytrip.model.Route;

public class SelfDriving implements ITravelMethod {
	private static final double minutePerKilometer = 1.8;

	public int estimateCost() {
		// flat fee
		return 12000;
	}

	private double estimateTime(double distance) {
		return distance * minutePerKilometer;
	}

	@Override
	public void performTrip(Route route) {
		System.out.printf("Performing an ride %s using owned vehicle...\n", route.toString());
		System.out.printf("Time estimated: %.1f minutes\n", estimateTime(route.getDistance()));
		System.out.printf("Cost: Rp%10d\n", estimateCost());
	}
}
