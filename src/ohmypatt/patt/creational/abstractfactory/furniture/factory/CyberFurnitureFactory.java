package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.cyber.CyberBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.cyber.CyberSofa;

public class CyberFurnitureFactory implements FurnitureFactory {
	public Sofa createSofa() {
		return new CyberSofa();
	}

	public Bed createBed() {
		return new CyberBed();
	}
}
