package ohmypatt.patt.structural.facade.room.model;

public class SmartTV extends BaseTV {
	public void powerOn() {
		System.out.println("Turn Smart TV on");
		getTvStatus();
	}

	public void powerOff() {
		System.out.println("Turn Smart TV off");
	}
}
