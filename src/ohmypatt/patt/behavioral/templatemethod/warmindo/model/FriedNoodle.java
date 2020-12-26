package ohmypatt.patt.behavioral.templatemethod.warmindo.model;

public class FriedNoodle extends Noodle {
	public FriedNoodle() {
		super();
	}

	public FriedNoodle(String name) {
		super(name);
	}

	@Override
	protected void prepareIngredients() {
		System.out.println("Put seasoning and ketchup to plate/bowl..");
	}

	@Override
	protected void serve() {
		System.out.println("Drain the water from noodle..");
		System.out.println("Serving dried noodle to plate/bowl");
	}

	@Override
	public String getName() {
		return "Mie Goreng " + name;
	}
}