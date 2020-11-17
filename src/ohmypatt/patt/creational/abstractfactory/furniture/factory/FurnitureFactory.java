package ohmypatt.patt.creational.abstractfactory.furniture.factory;

import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;

public interface FurnitureFactory {
	public Furniture createFurniture(String type);
}
