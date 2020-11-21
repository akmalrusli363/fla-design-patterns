package ohmypatt.patt.structural.adapter.smartphone.adapter.charger;

import ohmypatt.patt.structural.adapter.smartphone.adapter.microusb.GenericMobilePort;

public class MiCharger {
	public void charge(GenericMobilePort port) {
		port.chargeWithMicroUsb();
	}
}
