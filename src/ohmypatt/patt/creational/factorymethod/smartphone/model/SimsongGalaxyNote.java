package ohmypatt.patt.creational.factorymethod.smartphone.model;

public class SimsongGalaxyNote extends Smartphone {
	public void assemble() {
		setCapacity(128);
		setDeviceType("phablet");
		setScreenSize(6.6f);
		System.out.println("Assembling Simsong Galaxy Note...");
	}

	public String describe() {
		return "Simsong Galaxy Note";
	}
	// some codes...
}
