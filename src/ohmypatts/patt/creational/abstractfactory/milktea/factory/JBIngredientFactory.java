package ohmypatts.patt.creational.abstractfactory.milktea.factory;

public class JBIngredientFactory implements IngredientFactory {

	@Override
	public String generateMilk() {
		return "JB Milk";
	}

	@Override
	public String generateLeaf() {
		return "JB Tea";
	}

	@Override
	public String generateFruit() {
		return "JB Pomegranate";
	}

}
