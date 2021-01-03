package ohmypatt.patt.structural.bridge.remote.after.model;

public class SuperRemote extends Remote {
	public SuperRemote(Device device) {
		super(device);
	}

	// an extra method for extra functionality
	public void setVolume(int volume) {
		device.setVolume(volume);
	}

	public void isTurnedOn() {
		System.out.println(device.isTurnedOn());
	}

	// keep it abstract, make sure that commands need to be delegated to
	// implementator
	public void getInformation() {
		System.out.println("Device information:");
		System.out.println("----------------------");
		System.out.println(device.toString());
	}
}