package ohmypatt.patt.creational.factorymethod.smartphone.main;

import ohmypatt.patt.creational.factorymethod.smartphone.factory.SimsongFactory;
import ohmypatt.patt.creational.factorymethod.smartphone.model.Smartphone;

public class Main {

	public Main() {
		SimsongFactory factory = new SimsongFactory();
		Smartphone phone1 = factory.createSmartphone("Galaxy Prime");
		System.out.println(phone1.toString());
		System.out.println("-----------------------");
		
		Smartphone phone2 = factory.createSmartphone("Fold Z");
		System.out.println(phone2.toString());
		System.out.println("-----------------------");
		
		Smartphone phone3 = factory.createSmartphone("Galaxy Note");
		System.out.println(phone3.toString());
		System.out.println("-----------------------");
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
