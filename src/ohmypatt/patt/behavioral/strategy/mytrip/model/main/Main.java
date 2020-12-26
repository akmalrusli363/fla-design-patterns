package ohmypatt.patt.behavioral.strategy.mytrip.model.main;

import ohmypatt.patt.behavioral.strategy.mytrip.model.MyTrip;
import ohmypatt.patt.behavioral.strategy.mytrip.model.Route;
import ohmypatt.patt.behavioral.strategy.mytrip.strategy.Ojol;
import ohmypatt.patt.behavioral.strategy.mytrip.strategy.PublicTransportation;

public class Main {

	public Main() {
		Route routeToCampus = new Route("Kos bintaro", "Kampus UI depok", 15);
		MyTrip myTripToCampus = new MyTrip(routeToCampus, new Ojol());
		myTripToCampus.performTrip();
		
		System.out.println();
		System.out.println("Ganti moda ke transportasi umum");
		System.out.println();

		myTripToCampus.setTravelMethod(new PublicTransportation());
		myTripToCampus.performTrip();
	}

	public static void main(String[] args) {
		new Main();
	}

}
