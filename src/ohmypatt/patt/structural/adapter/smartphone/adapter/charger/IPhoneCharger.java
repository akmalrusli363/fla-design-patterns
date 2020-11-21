package ohmypatt.patt.structural.adapter.smartphone.adapter.charger;

import ohmypatt.patt.structural.adapter.smartphone.adapter.lightning.Lightning;

public class IPhoneCharger {
	private static IPhoneCharger instance;

	private IPhoneCharger() {
	}

	public synchronized static IPhoneCharger getInstance() {
		if (instance == null) {
			instance = new IPhoneCharger();
		}
		return instance;
	}
	
	public void charge(Lightning port) {
		port.chargeWithLightning();;
	}
}
