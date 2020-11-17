package ohmypatt.patt.creational.abstractfactory.furniture.model.cyber;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class CyberBed extends Bed {
	public void assemble() {
		// assembling Cyber-styled bed...
		setCover("Syntethic Fiber");
		setMaterial(FurnitureMaterial.TITANIUM_PATINA_COMPOSITE);
		setPillowFoam("Super memory AI-Powered Pillow");
	}

	public String describe() {
		return "Cyber-styled Bed";
	}
}