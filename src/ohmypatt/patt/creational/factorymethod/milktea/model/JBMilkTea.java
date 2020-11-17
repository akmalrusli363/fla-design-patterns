package ohmypatt.patt.creational.factorymethod.milktea.model;

public class JBMilkTea extends MilkTea {

	@Override
	public void prepare() {
		milk = "JB Milk";
		leaf = "JB Leaf";
		System.out.println("Preparing milk & leaf");
	}

	@Override
	protected String getBeverageName() {
		return "JB Milk Tea";
	}

}
