package ohmypatt.patt.structural.adapter.smartphone.adapter.lightning;

public class IPhonePort implements Lightning {
	@Override
	public void chargeWithLightning() {
		System.out.println("The phone is charged with Lightning port");
	}
}
