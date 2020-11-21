package ohmypatt.patt.structural.facade.room.model;

public class LedTV extends BaseTV {
	public void powerOn() {
		System.out.println("Turn LED TV on");
		getTvStatus();
	}

	public void powerOff() {
		System.out.println("Turn LED TV off");
	}
}
