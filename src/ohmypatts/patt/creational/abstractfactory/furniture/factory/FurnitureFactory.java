package ohmypatts.patt.creational.abstractfactory.furniture.factory;

import ohmypatts.patt.creational.abstractfactory.furniture.model.Furniture;

public interface FurnitureFactory {
	public Furniture createFurniture(String type);
}
