package ohmypatts.patt.creational.abstractfactory.furniture.factory;

import ohmypatts.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatts.patt.creational.abstractfactory.furniture.model.Furniture;
import ohmypatts.patt.creational.abstractfactory.furniture.model.cyber.CyberBed;
import ohmypatts.patt.creational.abstractfactory.furniture.model.medieval.MedievalBed;
import ohmypatts.patt.creational.abstractfactory.furniture.model.victorian.VictorianBed;

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