package ohmypatt.patt.creational.factorymethod.smartphone.factory;

import ohmypatt.patt.creational.factorymethod.smartphone.model.Smartphone;

public interface SmartphoneFactory {
	public Smartphone createSmartphone(String type);
}
