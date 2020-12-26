package ohmypatt.patt.behavioral.templatemethod.warmindo.model;

public class ChickenNoodle extends Noodle {
	public ChickenNoodle() {
		super();
	}

	public ChickenNoodle(String name) {
		super(name);
	}

	@Override
	protected void prepareIngredients() {
		System.out.println("Put seasoning to plate/bowl..");
	}

	/*
	 * This way is different from NoodleSoup and FriedNoodle because of chicken &
	 * dumpling use in cook()
	 */
	@Override
	protected void cook() {
		System.out.println("Cooking minced chicken and dumpling...");
		System.out.println("Cooking noodle...");
	}

	@Override
	protected void serve() {
		System.out.println("Drain the water from noodle..");
		System.out.println("Mix served chicken noodle to plate/bowl..");
		System.out.println("Prepare dumpling and vegetables to served noodle");
	}

	@Override
	public String getName() {
		return "Mie Ayam " + name;
	}
}