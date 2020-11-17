package ohmypatt.patt.creational.abstractfactory.milktea.shop;

import ohmypatt.patt.creational.abstractfactory.milktea.model.Beverage;

/**
 * This class/subclass serves abstract factory methods in which to be applied by factories which produces
 * kinds of products (such as MilkTea, Tea, or kinds of beverages)
 * 
 * @author akmalrusli363
 */
public abstract class DrinkShop {
	public Beverage orderBeverage(String type) {
		Beverage result = null;
		result = createBeverage(type);
		result.prepare();
		result.make();
		result.serve();
		return result;
	}
	
	/**
	 * This is the key part, this abstract method is deployed across subclasses
	 * to deploy it's own variation of their factory (eg. JBFactory to deploy it's own JBMilk and JBMilkTea).
	 * 
	 * Don't forget to put their supertype of the product to ensure flexibility and expandible of hierarchy.
	 * 
	 * NOTE: This factory products lot of object class/abstraction with factory's own variances.
	 * 
	 * @param type - the type of Beverage (variance of Beverage including MilkTea, Milk, Juice, etc.)
	 * @return Beverage - object who extends/implements Beverage type (such as MilkTea, Milk, etc).
	 */
	protected abstract Beverage createBeverage(String type);
}
