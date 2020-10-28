package ohmypatts.patt.creational.abstractfactory.furniture.model.cyber;

import ohmypatts.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

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