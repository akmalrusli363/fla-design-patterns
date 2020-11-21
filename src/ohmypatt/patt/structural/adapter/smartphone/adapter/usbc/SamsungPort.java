package ohmypatt.patt.structural.adapter.smartphone.adapter.usbc;

public class SamsungPort implements UsbTypeC {
	@Override
	public void chargeWithUsbTypeC() {
		System.out.println("The phone is charged with USB Type C");
	}
}
