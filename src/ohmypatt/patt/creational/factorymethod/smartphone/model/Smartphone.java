package ohmypatt.patt.creational.factorymethod.smartphone.model;

public abstract class Smartphone {
	private float screenSize;
	private int capacity;
	private String deviceType;

	public Smartphone() {
		assemble();
	}
	
	public abstract void assemble();

	public abstract String describe();

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return String.format("%s (screen size: %.2f inch | capacity: %dGB | type: %s)",
				describe(), screenSize, capacity, deviceType);
	}
	
}
