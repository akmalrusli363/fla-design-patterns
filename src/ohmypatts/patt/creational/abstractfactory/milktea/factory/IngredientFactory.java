package ohmypatts.patt.creational.abstractfactory.milktea.factory;

/**
 * This interface serves ingredient for supporting store as main abstract factory
 *
 * @author akmalrusli363
 */
public interface IngredientFactory {
	String generateMilk();
	String generateLeaf();
	String generateFruit();
}
