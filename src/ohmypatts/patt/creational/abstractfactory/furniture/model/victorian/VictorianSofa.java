package ohmypatts.patt.creational.abstractfactory.furniture.model.victorian;

import ohmypatts.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

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