package ohmypatt.patt.structural.adapter.smartphone.adapter.usbc;

import ohmypatt.patt.structural.adapter.smartphone.adapter.microusb.GenericMobilePort;

public class MicroUsbAdapter implements UsbTypeC {
	private GenericMobilePort microPort;
	
	public MicroUsbAdapter(GenericMobilePort microPort) {
		this.microPort = microPort;
	}

	@Override
	public void chargeWithUsbTypeC() {
		microPort.chargeWithMicroUsb();
	}
}
