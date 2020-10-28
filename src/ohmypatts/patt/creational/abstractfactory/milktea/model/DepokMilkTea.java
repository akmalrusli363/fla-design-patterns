package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

public class DepokMilkTea extends MilkTea {

	public DepokMilkTea(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		super.prepare();
		System.out.println("Preparing milk & leaf");
	}

	@Override
	public String getBeverageName() {
		return "Depok Milk Tea";
	}

}
