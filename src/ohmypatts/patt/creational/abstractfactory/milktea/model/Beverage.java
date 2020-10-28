package ohmypatts.patt.creational.abstractfactory.milktea.model;

import ohmypatts.patt.creational.abstractfactory.milktea.factory.IngredientFactory;

/**
 * This superclass serves kinds of beverages (such as MilkTea, Milk, Tea, or other kinds of beverages).
 * Make sure that this class should declared as abstract class or interface.
 * 
 * @author akmalrusli363
 */
public abstract class Beverage {
	protected IngredientFactory factory;

	public Beverage(IngredientFactory factory) {
		this.factory = factory;
	}

	public abstract void prepare();

	public abstract void make();

	public void serve() {
		System.out.println("Serve to customer");
	}

	@Override
	public abstract String toString();
	
	protected abstract String getBeverageName();

}