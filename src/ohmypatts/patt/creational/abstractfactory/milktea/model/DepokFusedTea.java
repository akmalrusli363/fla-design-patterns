package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

public class DepokFusedTea extends MilkTea {

	public DepokFusedTea(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		super.prepare();
		System.out.println("Preparing strawberry & leaf");
	}

	@Override
	public String getBeverageName() {
		return "Depok Berry Fused Tea";
	}

}
