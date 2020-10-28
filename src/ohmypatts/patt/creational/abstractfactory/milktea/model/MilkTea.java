package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

/**
 * This class extends from Beverage class. Even this was a type of Beverage, this class 
 * still need to be extended to subclasses who defined by a factory declared from abstract factory.
 * 
 * @author akmalrusli363
 */
public abstract class MilkTea extends Beverage {
	protected String leaf, milk;
	
	public MilkTea(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		milk = factory.generateLeaf();
		leaf = factory.generateMilk();
	}
	
	@Override
	public void make() {
		System.out.println("Blend the leaf and milk");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBeverageName());
		if (leaf != null && milk != null && !leaf.isEmpty() && !milk.isEmpty()) {
			sb.append(" created from ").append(leaf).append(" and ").append(milk);
		}
		return getBeverageName();
		
	}

}
