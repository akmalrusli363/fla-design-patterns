package ohmypatt.patt.structural.adapter.smartphone.adapter.usbc;

import ohmypatt.patt.structural.adapter.smartphone.adapter.lightning.IPhonePort;

public class LightningAdapter implements UsbTypeC {
	private IPhonePort iPort;
	
	public LightningAdapter(IPhonePort iPort) {
		this.iPort = iPort;
	}
	
	@Override
	public void chargeWithUsbTypeC() {
		iPort.chargeWithLightning();
	}
}
