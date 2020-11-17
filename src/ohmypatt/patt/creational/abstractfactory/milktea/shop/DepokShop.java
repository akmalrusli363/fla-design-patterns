package ohmypatt.patt.creational.abstractfactory.milktea.shop;

import ohmypatt.patt.creational.abstractfactory.milktea.factory.IngredientFactory;
import ohmypatt.patt.creational.abstractfactory.milktea.factory.JBIngredientFactory;
import ohmypatt.patt.creational.abstractfactory.milktea.model.Beverage;
import ohmypatt.patt.creational.abstractfactory.milktea.model.DepokFusedTea;
import ohmypatt.patt.creational.abstractfactory.milktea.model.DepokMilk;
import ohmypatt.patt.creational.abstractfactory.milktea.model.DepokMilkTea;

public class DepokShop extends DrinkShop {

	private IngredientFactory factory;

	public DepokShop() {
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
			result = new DepokMilkTea(factory);
		} else if (type.equals("milk")) {
			result = new DepokMilk(factory);
		} else if (type.equals("infused")) {
			result = new DepokFusedTea(factory);
		}
		
		return result;
	}

}
