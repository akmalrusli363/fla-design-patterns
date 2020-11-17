package ohmypatt.patt.creational.abstractfactory.furniture.model.cyber;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class CyberSofa extends Sofa {
	public void assemble() {
		// assembling Cyber-styled sofa...
		setMaterial(FurnitureMaterial.TITANIUM_PATINA_COMPOSITE);
		setCapacity(FurnitureCapacity.L);
	}

	public String describe() {
		return "Cyber-styled Sofa";
	}
}