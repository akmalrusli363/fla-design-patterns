package ohmypatts.patt.creational.abstractfactory.furniture.main;

import java.util.ArrayList;

import ohmypatts.patt.creational.abstractfactory.furniture.factory.BedFactory;
import ohmypatts.patt.creational.abstractfactory.furniture.factory.SofaFactory;
import ohmypatts.patt.creational.abstractfactory.furniture.model.Furniture;

public class ChandraFurniture {
	public ArrayList<Furniture> createFurnitureSets() {
		ArrayList<Furniture> setFurniture = new ArrayList<>();
		
		SofaFactory pabrikSofa = new SofaFactory();
		BedFactory pabrikRanjang = new BedFactory();

		Furniture sofa = pabrikSofa.createFurniture("Victorian");
		Furniture ranjang = pabrikRanjang.createFurniture("Medieval");
	    
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