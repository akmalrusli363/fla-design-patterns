package ohmypatt.patt.creational.factorymethod.smartphone.model;

public class SimsongGalaxyPrime extends Smartphone {
	public void assemble() {
		setCapacity(64);
		setDeviceType("full-screen display");
		setScreenSize(5.99f);
		System.out.println("Assembling Simsong Galaxy Prime...");
	}

	public String describe() {
		return "Simsong Galaxy Prime";
	}
	// some codes...
}
