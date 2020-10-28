package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

public class JBMilk extends Milk {

	public JBMilk(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		super.prepare();
		System.out.println("Preparing milk");
	}
	
	@Override
	public String getBeverageName() {
		return "JB Milk Cup";
	}
	
}
