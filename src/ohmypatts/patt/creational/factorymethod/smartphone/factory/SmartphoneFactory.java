package ohmypatts.patt.creational.factorymethod.smartphone.factory;

import ohmypatts.patt.creational.factorymethod.smartphone.model.Smartphone;

public interface SmartphoneFactory {
	public Smartphone createSmartphone(String type);
}
