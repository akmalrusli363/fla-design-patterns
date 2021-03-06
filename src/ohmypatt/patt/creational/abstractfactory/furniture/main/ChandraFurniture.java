package ohmypatt.patt.creational.abstractfactory.furniture.main;

import java.util.ArrayList;

import ohmypatt.patt.creational.abstractfactory.furniture.factory.FurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;

public class ChandraFurniture {
	public ArrayList<Furniture> createFurnitureSets() {
		ArrayList<Furniture> setFurniture = new ArrayList<>();
		
		FurnitureFactory furnitureFactory = new ChandraFurnitureFactory();

		Furniture sofa = furnitureFactory.createSofa();
		Furniture ranjang = furnitureFactory.createBed();
	    
	    System.out.println("------------------");

		System.out.println("Chandra Victorian Sofa:");
		System.out.println(sofa.getFullDescription());
	    
	    System.out.println("------------------");

		System.out.println("Chandra Medieval Sofa:");
		System.out.println(ranjang.getFullDescription());
	    
	    System.out.println("------------------");
	    
	    setFurniture.add(sofa);
	    setFurniture.add(ranjang);

		System.out.println("Selamat menikmati produk furniture kami :D");
		
		return setFurniture;
	}
}