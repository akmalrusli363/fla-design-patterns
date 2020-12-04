package ohmypatt.patt.structural.adapter.smartphone.model.iphone;

import ohmypatt.patt.structural.adapter.smartphone.adapter.charger.IPhoneCharger;
import ohmypatt.patt.structural.adapter.smartphone.adapter.lightning.Lightning;

public class IPhone {

	private final String type;
	private String storage;
	private final IPhoneCharger charger;

	public IPhone(String type) {
		this.type = type;
		charger = IPhoneCharger.getInstance();
	}

	public IPhone(String type, String storage) {
		this(type);
		this.storage = storage;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getType() {
		return type;
	}

	public IPhoneCharger getCharger() {
		return charger;
	}

	public void charge(Lightning port) {
		charger.charge(port);
	}

	@Override
	public String toString() {
		return "IPhone " + type + " [storage=" + storage + "]";
	}

}
