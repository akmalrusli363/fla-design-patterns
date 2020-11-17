package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;
import ohmypatt.patt.creational.abstractfactory.furniture.model.cyber.CyberBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.medieval.MedievalBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.victorian.VictorianBed;

public class BedFactory implements FurnitureFactory {
	public Furniture createFurniture(String type) {
		Bed bed = null;
		if (type.equals("Victorian")) {
			bed = new VictorianBed();
		} else if (type.equals("Cyber")) {
			bed = new CyberBed();
		} else if (type.equals("Medieval")) {
			bed = new MedievalBed();
		}
		return bed;
	}
}