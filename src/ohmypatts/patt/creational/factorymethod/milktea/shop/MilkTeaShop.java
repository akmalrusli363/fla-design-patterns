package ohmypatts.patt.creational.factorymethod.milktea.shop;

import ohmypatts.patt.creational.factorymethod.milktea.model.MilkTea;

public abstract class MilkTeaShop {
	public MilkTea orderMilkTea(String type) {
		MilkTea result = null;
		result = createMilkTea(type);
		result.prepare();
		result.make();
		result.serve();
		return result;
	}
	
	/**
	 * This abstract method declares factory method to create their own object variance derived
	 * from an object abstraction/interface to be applied for each factory subclasses.
	 * 
	 * NOTE: This factory only produce one object class/abstraction with factory's own variances.
	 * 
	 * @param type - the type of MilkTea (variance of MilkTea)
	 * @return MilkTea - object who extends/implements MilkTea types.
	 */
	protected abstract MilkTea createMilkTea(String type);
}
