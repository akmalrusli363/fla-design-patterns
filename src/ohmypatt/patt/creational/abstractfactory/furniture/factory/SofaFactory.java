package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.cyber.CyberSofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.medieval.MedievalSofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.victorian.VictorianSofa;

public class SofaFactory implements FurnitureFactory {
	public Furniture createFurniture(String type) {
		Sofa sofa = null;
		if (type.equals("Victorian")) {
			sofa = new VictorianSofa();
		} else if (type.equals("Cyber")) {
			sofa = new CyberSofa();
		} else if (type.equals("Medieval")) {
			sofa = new MedievalSofa();
		}
		return sofa;
	}
}
