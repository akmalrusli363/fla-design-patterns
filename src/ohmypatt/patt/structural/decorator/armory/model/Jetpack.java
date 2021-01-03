package ohmypatt.patt.structural.decorator.armory.model;

public class Jetpack extends ArmorDecorator {
	private int baseCapacity, fuel;

	public Jetpack(Armor innerSkin, String name, int baseCapacity) {
		super(innerSkin, name);
		this.baseCapacity = baseCapacity;
	}

	private void refuel() {
		this.fuel = baseCapacity;
		System.out.printf("Refuel your jetpack...\n");
	}
	
	private void checkFuel() {
		System.out.printf("Current fuel in your jetpack: (%d/%d liter)\n", fuel, baseCapacity);
	}

	@Override
	public void attach() {
		this.innerSkin.attach();
		System.out.printf("Attaching jetpack (%s, %d Liter) to your armor...\n", name, baseCapacity);
		refuel();
		checkFuel();
	}
}