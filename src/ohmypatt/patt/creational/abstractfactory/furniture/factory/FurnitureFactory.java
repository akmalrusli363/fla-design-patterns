package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;

public interface FurnitureFactory {
	Sofa createSofa();
	Bed createBed();
}