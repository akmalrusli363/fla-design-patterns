package ohmypatts.patt.creational.abstractfactory.furniture.model;

import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureCapacity;
import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public abstract class Sofa implements Furniture {
	private FurnitureMaterial material;
	private FurnitureCapacity capacity;
	
	public Sofa() {
		assemble();
		System.out.println("Building sofa...");
	}

	public void setMaterial(FurnitureMaterial material) {
		this.material = material;
	}

	public void setCapacity(FurnitureCapacity capacity) {
		this.capacity = capacity;
	}

	public FurnitureMaterial getMaterial() {
		return material;
	}

	public FurnitureCapacity getCapacity() {
		return capacity;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(describe());
		sb.append("\nMaterial: ").append(getMaterial());
		sb.append("\nCapacity: ").append(getCapacity());
		return sb.toString();
	}
}
