package ohmypatts.patt.creational.factorymethod.milktea.shop;

import ohmypatts.patt.creational.factorymethod.milktea.model.JBMilk;
import ohmypatts.patt.creational.factorymethod.milktea.model.JBMilkTea;
import ohmypatts.patt.creational.factorymethod.milktea.model.MilkTea;

public class JBShop extends MilkTeaShop {

	/**
	 * Apply their own method contents to return their object
	 */
	@Override
	protected MilkTea createMilkTea(String type) {
		MilkTea result = null;
		if (type.equals("original")) {
			result = new JBMilkTea();
		} else if (type.equals("milk")) {
			result = new JBMilk();
		}
		return result;
	}

}
