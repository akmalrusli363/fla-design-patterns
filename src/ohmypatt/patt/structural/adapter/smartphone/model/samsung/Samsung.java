package ohmypatt.patt.structural.adapter.smartphone.model.samsung;

import ohmypatt.patt.structural.adapter.smartphone.adapter.charger.SamsungCharger;
import ohmypatt.patt.structural.adapter.smartphone.adapter.usbc.UsbTypeC;

public class Samsung {
	private String storage, ram, batteryDuration;
	private final SamsungCharger charger;
	
	public Samsung() {
		charger = SamsungCharger.getInstance();
	}
	
	public Samsung(String storage, String ram, String batteryDuration) {
		this();
		this.storage = storage;
		this.ram = ram;
		this.batteryDuration = batteryDuration;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getRam() {
		return ram;
	}

	public String getBatteryDuration() {
		return batteryDuration;
	}

	public void setBatteryDuration(String batteryDuration) {
		this.batteryDuration = batteryDuration;
	}
	
	public SamsungCharger getCharger() {
		return charger;
	}
	
	public void charge(UsbTypeC port) {
		charger.charge(port);
	}

	@Override
	public String toString() {
		return "Samsung [storage=" + storage + ", ram=" + ram + ", batteryDuration=" + batteryDuration + "]";
	}
	
}
