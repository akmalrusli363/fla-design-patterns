package ohmypatt.patt.structural.adapter.smartphone.adapter.lightning;

import ohmypatt.patt.structural.adapter.smartphone.adapter.usbc.SamsungPort;

public class UsbTypeCAdapter implements Lightning {
	private SamsungPort cPort;

	public UsbTypeCAdapter(SamsungPort cPort) {
		this.cPort = cPort;
	}

	@Override
	public void chargeWithLightning() {
		cPort.chargeWithUsbTypeC();
	}
}
