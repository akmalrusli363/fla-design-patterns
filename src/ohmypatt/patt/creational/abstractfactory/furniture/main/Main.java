package ohmypatt.patt.creational.abstractfactory.furniture.main;

import ohmypatt.patt.creational.abstractfactory.furniture.factory.CyberFurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.factory.FurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.factory.MedievalFurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.factory.VictorianFurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Furniture;

public class Main {

	public Main() {
		createCustomFurnitureSets();
		
		System.out.println("Minta sofa dari Chandra Furniture...");
		System.out.println();
		new ChandraFurniture().createFurnitureSets();
	}

	private void createCustomFurnitureSets() {
		FurnitureFactory cyberFactory = new CyberFurnitureFactory();
		FurnitureFactory medievalFactory = new MedievalFurnitureFactory();
		FurnitureFactory victoriaFactory = new VictorianFurnitureFactory();
		
		Furniture furniture1 = cyberFactory.createBed();
		System.out.println(furniture1.toString());
		System.out.println("-----------------------");
		
		Furniture furniture2 = medievalFactory.createSofa();
		System.out.println(furniture2.toString());
		System.out.println("-----------------------");
		
		Furniture furniture3 = victoriaFactory.createSofa();
		System.out.println(furniture3.toString());
		System.out.println("-----------------------");
		
		Furniture furniture4 = victoriaFactory.createBed();
		System.out.println(furniture4.toString());
		System.out.println("-----------------------");
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
