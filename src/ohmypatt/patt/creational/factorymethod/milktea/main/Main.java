package ohmypatt.patt.creational.factorymethod.milktea.main;

import ohmypatt.patt.creational.factorymethod.milktea.model.MilkTea;
import ohmypatt.patt.creational.factorymethod.milktea.shop.JBShop;

public class Main {

	public Main() {
		JBShop shop = new JBShop();
		MilkTea drink = shop.orderMilkTea("original");
		System.out.println(drink.toString() + "\n");
		MilkTea drink2 = shop.orderMilkTea("milk");
		System.out.println(drink2.toString() + "\n");
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
