package ohmypatt.patt.creational.factorymethod.smartphone.factory;

import ohmypatt.patt.creational.factorymethod.smartphone.model.SimsongFoldZ;
import ohmypatt.patt.creational.factorymethod.smartphone.model.SimsongGalaxyNote;
import ohmypatt.patt.creational.factorymethod.smartphone.model.SimsongGalaxyPrime;
import ohmypatt.patt.creational.factorymethod.smartphone.model.Smartphone;

public class SimsongFactory implements SmartphoneFactory {
	public Smartphone createSmartphone(String type) {
		Smartphone smartphone = null;
		if (type.equals("Galaxy Prime")) {
			smartphone = new SimsongGalaxyPrime();
		} else if (type.equals("Galaxy Note")) {
			smartphone = new SimsongGalaxyNote();
		} else if (type.equals("Fold Z")) {
			smartphone = new SimsongFoldZ();
		}
		return smartphone;
	}
}