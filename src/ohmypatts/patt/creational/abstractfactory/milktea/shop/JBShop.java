package ohmypatts.patt.creational.abstractfactory.milktea.shop;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;
import ohmypatts.patt.creational.abstractfactory.milktea.factory.JBIngredientFactory;
import ohmypatts.patt.creational.abstractfactory.milktea.model.Beverage;
import ohmypatts.patt.creational.abstractfactory.milktea.model.JBMilk;
import ohmypatts.patt.creational.abstractfactory.milktea.model.JBMilkTea;

public class JBShop extends DrinkShop {

	private IngredientFactory factory;

	public JBShop() {
		factory = new JBIngredientFactory();
	}

	/**
	 * This is the key part, to deploy it's own variation of their factory.
	 * This will serve their version of milkTea
	 */
	@Override
	protected Beverage createBeverage(String type) {
		Beverage result = null;

		if (type.equals("original")) {
			result = new JBMilkTea(factory);
		} else if (type.equals("milk")) {
			result = new JBMilk(factory);
		}
		
		return result;
	}

}
