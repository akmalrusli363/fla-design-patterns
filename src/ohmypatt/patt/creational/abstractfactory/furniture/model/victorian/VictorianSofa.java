package ohmypatt.patt.creational.abstractfactory.furniture.model.victorian;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatt.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public class VictorianSofa extends Sofa {
	public void assemble() {
		// assembling Victorian-styled sofa...
		setMaterial(FurnitureMaterial.IRISH_WOOD);
		setCapacity(FurnitureCapacity.XL);
	}

	public String describe() {
		return "Victorian Sofa";
	}
}