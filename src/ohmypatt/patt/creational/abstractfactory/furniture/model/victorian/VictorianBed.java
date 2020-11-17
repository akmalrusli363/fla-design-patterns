package ohmypatt.patt.creational.abstractfactory.furniture.model.victorian;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class VictorianBed extends Bed {
	public void assemble() {
		// assembling Victorian-styled bed...
		setCover("Irish Wool");
		setMaterial(FurnitureMaterial.SPRING_SALTER_COMPOSITE);
		setPillowFoam("Luxe Cotton");
	}

	public String describe() {
		return "Victorian Bed";
	}
}