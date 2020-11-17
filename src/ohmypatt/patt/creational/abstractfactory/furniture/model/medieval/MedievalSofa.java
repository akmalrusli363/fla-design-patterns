package ohmypatt.patt.creational.abstractfactory.furniture.model.medieval;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class MedievalSofa extends Sofa {
	public void assemble() {
		// assembling Medieval-styled sofa...
		setMaterial(FurnitureMaterial.SPRUCE_WOOD);
		setCapacity(FurnitureCapacity.M);
	}

	public String describe() {
		return "Cyber-styled Sofa";
	}
}