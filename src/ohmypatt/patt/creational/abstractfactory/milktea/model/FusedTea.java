package ohmypatt.patt.creational.abstractfactory.milktea.model;

import ohmypatt.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

/**
 * This class extends from Beverage class. Even this was a type of Beverage, this class 
 * still need to be extended to subclasses who defined by a factory declared from abstract factory.
 * 
 * @author akmalrusli363
 */
public abstract class FusedTea extends Beverage {
	protected String leaf, fruit;
	
	public FusedTea(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		leaf = factory.generateMilk();
		fruit = factory.generateFruit();
	}
	
	@Override
	public void make() {
		System.out.println("Blend the leaf and fruit");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBeverageName());
		if (leaf != null && fruit != null && !leaf.isEmpty() && !fruit.isEmpty()) {
			sb.append(" created from ").append(leaf).append(" and ").append(fruit);
		}
		return getBeverageName();
		
	}

}
