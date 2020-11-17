package ohmypatt.patt.creational.abstractfactory.milktea.main;

import ohmypatt.patt.creational.abstractfactory.milktea.model.Beverage;
import ohmypatt.patt.creational.abstractfactory.milktea.shop.DepokShop;
import ohmypatt.patt.creational.abstractfactory.milktea.shop.DrinkShop;
import ohmypatt.patt.creational.abstractfactory.milktea.shop.JBShop;

public class Main {

	public Main() {
		DrinkShop jktShop = new JBShop();
		DrinkShop depokShop = new DepokShop();
		
		Beverage drink = jktShop.orderBeverage("original");
		System.out.println(drink.toString() + "\n");
		Beverage drink2 = jktShop.orderBeverage("milk");
		System.out.println(drink2.toString() + "\n");
		
		Beverage drink3 = depokShop.orderBeverage("original");
		System.out.println(drink3.toString() + "\n");
		Beverage drink4 = depokShop.orderBeverage("milk");
		System.out.println(drink4.toString() + "\n");
		Beverage drink5 = depokShop.orderBeverage("infused");
		System.out.println(drink5.toString() + "\n");
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
