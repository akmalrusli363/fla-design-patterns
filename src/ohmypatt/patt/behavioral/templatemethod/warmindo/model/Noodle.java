package ohmypatt.patt.behavioral.templatemethod.warmindo.model;

public abstract class Noodle {
	protected String name = "";

	public Noodle() {
	}

	public Noodle(String name) {
		this.name = name;
	}

	public final void makeNoodle() {
		boilWater();
		prepareIngredients();
		cook();
		serve();
	}

	protected void boilWater() {
		System.out.println("Boiling water...");
	}

	protected abstract void prepareIngredients();

	protected void cook() {
		System.out.println("Cooking noodle...");
	}

	protected abstract void serve();

	public abstract String getName();
}