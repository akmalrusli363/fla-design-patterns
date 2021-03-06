package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.victorian.VictorianBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.victorian.VictorianSofa;

public class VictorianFurnitureFactory implements FurnitureFactory {
	public Sofa createSofa() {
		return new VictorianSofa();
	}

	public Bed createBed() {
		return new VictorianBed();
	}
}
