package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

public class JBMilkTea extends MilkTea {

	public JBMilkTea(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		super.prepare();
		System.out.println("Preparing milk & leaf");
	}

	@Override
	public String getBeverageName() {
		return "JB Milk Tea";
	}

}
