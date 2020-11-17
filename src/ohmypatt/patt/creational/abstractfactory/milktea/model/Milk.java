package ohmypatt.patt.creational.abstractfactory.milktea.model;

import ohmypatt.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

/**
 * This class extends from Beverage class. Even this was a type of Beverage, this class 
 * still need to be extended to subclasses who defined by a factory declared from abstract factory.
 * 
 * @author akmalrusli363
 */
public abstract class Milk extends Beverage {
	protected String milk;
	
	public Milk(IngredientFactory factory) {
		super(factory);
	}

	@Override
	public void prepare() {
		milk = factory.generateMilk();
	}
	
	@Override
	public void make() {
		System.out.println("Blend milk");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBeverageName());
		if (milk != null && !milk.isEmpty()) {
			sb.append(" created from ").append(milk);
		}
		return getBeverageName();
		
	}

}
