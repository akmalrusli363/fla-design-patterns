package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

public class DepokMilk extends Milk {

	public DepokMilk(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		super.prepare();
		System.out.println("Preparing milk");
	}

	@Override
	public String getBeverageName() {
		return "Depok Milk Cup";
	}
	
}
