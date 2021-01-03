package ohmypatt.patt.structural.bridge.remote.after.model;

public class Remote {
	protected Device device;

	public Remote(Device device) {
		this.device = device;
	}

	public void togglePower() {
		if (device.isTurnedOn()) {
			device.turnOn();
		} else {
			device.turnOff();
		}
	}

	public void increaseVolume() {
		device.setVolume(device.getVolume() + 1);
	}

	public void decreaseVolume() {
		device.setVolume(device.getVolume() - 1);
	}

	public void toggleMute() {
		device.toggleMute();
	}

	public void next() {
		device.next();
	}

	public void previous() {
		device.previous();
	}
}