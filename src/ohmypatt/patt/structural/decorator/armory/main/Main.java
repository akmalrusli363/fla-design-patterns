package ohmypatt.patt.structural.decorator.armory.main;

import ohmypatt.patt.structural.decorator.armory.model.Armor;
import ohmypatt.patt.structural.decorator.armory.model.Chestplate;
import ohmypatt.patt.structural.decorator.armory.model.Jetpack;
import ohmypatt.patt.structural.decorator.armory.model.Wings;

public class Main {
	public Main() {
		Armor chestplate = new Chestplate("Iron Chestplate");
		Armor wingyChest = new Wings(chestplate, "Elytra");
		Armor jettyWingChest = new Jetpack(wingyChest, "Cyberjet", 100);
		jettyWingChest.attach();
	}

	public static void main(String[] args) {
		new Main();
	}
}
