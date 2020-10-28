package ohmypatts.patt.creational.factorymethod.smartphone.model;

public class SimsongFoldZ extends Smartphone {
	public void assemble() {
		setCapacity(512);
		setDeviceType("folded");
		setScreenSize(12.1f);
		System.out.println("Assembling Simsong Fold Z...");
	}

	public String describe() {
		return "Simsong Fold Z";
	}
	// some codes...
}