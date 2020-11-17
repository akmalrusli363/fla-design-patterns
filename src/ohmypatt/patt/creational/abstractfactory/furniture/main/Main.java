package ohmypatt.patt.creational.abstractfactory.furniture.main;

import ohmypatt.patt.creational.abstractfactory.furniture.factory.BedFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.factory.FurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.factory.SofaFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;

public class Main {

	public Main() {
		createCustomFurnitureSets();
		
		System.out.println("Minta sofa dari Chandra Furniture...");
		System.out.println();
		new ChandraFurniture().createFurnitureSets();
	}

	private void createCustomFurnitureSets() {
		FurnitureFactory sofaFactory = new SofaFactory();
		FurnitureFactory bedFactory = new BedFactory();
		
		Furniture furniture1 = sofaFactory.createFurniture("Cyber");
		System.out.println(furniture1.toString());
		System.out.println("-----------------------");
		
		Furniture furniture2 = bedFactory.createFurniture("Medieval");
		System.out.println(furniture2.toString());
		System.out.println("-----------------------");
		
		Furniture furniture3 = sofaFactory.createFurniture("Victorian");
		System.out.println(furniture3.toString());
		System.out.println("-----------------------");
		
		Furniture furniture4 = bedFactory.createFurniture("Victorian");
		System.out.println(furniture4.toString());
		System.out.println("-----------------------");
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
