package ohmypatts.patt.creational.factorymethod.smartphone.factory;

import ohmypatts.patt.creational.factorymethod.smartphone.model.SimsongFoldZ;
import ohmypatts.patt.creational.factorymethod.smartphone.model.SimsongGalaxyNote;
import ohmypatts.patt.creational.factorymethod.smartphone.model.SimsongGalaxyPrime;
import ohmypatts.patt.creational.factorymethod.smartphone.model.Smartphone;

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