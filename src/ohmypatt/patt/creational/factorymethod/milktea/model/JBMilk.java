package ohmypatt.patt.creational.factorymethod.milktea.model;

public class JBMilk extends MilkTea {

	@Override
	public void prepare() {
		milk = "JB Milk";
		System.out.println("Preparing milk");
	}

	@Override
	protected String getBeverageName() {
		return "JB Milk Cup";
	}

}
