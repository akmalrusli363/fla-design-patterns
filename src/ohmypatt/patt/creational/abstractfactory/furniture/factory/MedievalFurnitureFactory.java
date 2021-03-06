package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.medieval.MedievalBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.medieval.MedievalSofa;

public class MedievalFurnitureFactory implements FurnitureFactory {
	public Sofa createSofa() {
		return new MedievalSofa();
	}

	public Bed createBed() {
		return new MedievalBed();
	}
}