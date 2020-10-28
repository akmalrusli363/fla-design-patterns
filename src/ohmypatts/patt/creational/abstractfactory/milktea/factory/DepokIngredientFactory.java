package ohmypatts.patt.creational.abstractfactory.milktea.factory;

public class DepokIngredientFactory implements IngredientFactory {

	@Override
	public String generateMilk() {
		return "Depok Soya Milk";
	}

	@Override
	public String generateLeaf() {
		return "Depok Jasmine Tea";
	}

	@Override
	public String generateFruit() {
		return "Depok Strawberry";
	}

}
