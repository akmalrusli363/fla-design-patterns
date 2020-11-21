package ohmypatt.patt.structural.adapter.smartphone.adapter.microusb;

public class GenericMobilePort implements MicroUsbPort {
	@Override
	public void chargeWithMicroUsb() {
		System.out.println("The phone is charged with Micro USB port");
	}
}
