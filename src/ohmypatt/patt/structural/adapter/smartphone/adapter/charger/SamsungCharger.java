package ohmypatt.patt.structural.adapter.smartphone.adapter.charger;

import ohmypatt.patt.structural.adapter.smartphone.adapter.usbc.UsbTypeC;

public class SamsungCharger {
	private static SamsungCharger instance;

	private SamsungCharger() {
	}

	/**
	 * Make the thread-safe instance of SamsungCharger using lazy loading
	 * @return
	 */
	public synchronized static SamsungCharger getInstance() {
		// Lazy loading
		if (instance == null) {
			instance = new SamsungCharger();
		}

		return instance;
	}

	public void charge(UsbTypeC port) {
		port.chargeWithUsbTypeC();
	}
}
