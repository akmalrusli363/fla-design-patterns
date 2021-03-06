package ohmypatt.patt.creational.abstractfactory.furniture.model.medieval;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class MedievalBed extends Bed {
	public void assemble() {
		// assembling Medieval-styled bed...
		setMaterial(FurnitureMaterial.SPRUCE_WOOD);
		setCover("Royal Cotton from Kanibalia");
		setPillowFoam("Kingdom's Feather");
	}

	public String describe() {
		return "Medieval-styled Bed";
	}
}