package ohmypatt.patt.behavioral.command.cardcombat.player;

import java.util.Random;

public class Damage {
	private final int minDamage, maxDamage;

	public Damage(int minDamage, int maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public int calculateDamage() {
		Random r = new Random();
		return minDamage + r.nextInt(maxDamage - minDamage);
	}
}
