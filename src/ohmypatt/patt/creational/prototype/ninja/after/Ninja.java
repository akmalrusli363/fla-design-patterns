package ohmypatt.patt.creational.prototype.ninja.after;

public class Ninja implements Cloneable {
	private String name, clan;
	private int level, hp;
	private AttackSkill attackSkill;

	private static final float BASE_HP_MODIFIER = 0.4f;
	private static final int BASE_HP = 1000;

	public Ninja(String name, String clan, AttackSkill attackSkill) {
		this(name, clan, 1, attackSkill);
	}

	public Ninja(String name, String clan, int level, AttackSkill attackSkill) {
		this(name, clan, level, attackSkill, Math.round(BASE_HP * BASE_HP_MODIFIER));
	}

	public Ninja(String name, String clan, int level, AttackSkill attackSkill, int hp) {
		this.name = name;
		this.clan = clan;
		this.level = level;
		this.attackSkill = attackSkill;
		this.hp = hp;
	}

	public int inflictDamage() {
		return Math.round(attackSkill.applyDamage() * (1 + (level * 0.25f)));
	}

	public void receiveDamage(int damage) {
		hp -= (hp - damage < 0) ? 0 : damage;
		System.out.println("Received " + damage + "HP Damage from opponent!");
	}

	public void attack(Ninja enemy) {
		System.out.printf("Giving damage %d HP to enemy...\n", this.inflictDamage());
		enemy.receiveDamage(inflictDamage());
	}

	public void performedJutsuAttack(Ninja enemy) {
		Ninja jutsu = null;
		try {
			jutsu = (Ninja) clone();
		} catch (CloneNotSupportedException ex) {
			// empty catch, to test that cloned object are completely implements Cloneable
		}
		// add equality/assertion test to prove that clone is not equal to original ninja
	    System.out.println("Clones equal to original ninja? " + jutsu.equals(this));
	    System.out.println("Clone's skill equal to original ninja? " + jutsu.attackSkill.equals(this.attackSkill));
	    System.out.println("Is clone deep-cloned to original ninja? " + isDeepCloned(jutsu));
	    
		// change ninja name & drop base damage to 20
		jutsu.name = "Juttoro";
		jutsu.attackSkill.setOverallDamage(20);

		int jutsuCloneMultiplier = 2 * Math.round(1 + (level * 0.3f));
		int jutsuDamage = jutsuCloneMultiplier * jutsu.inflictDamage();
		int totalDamage = this.inflictDamage() + jutsuDamage;

		System.out.printf("Giving %s jutsu damage %d HP x %d clones to enemy...\n", jutsu.name, jutsu.inflictDamage(),
				jutsuCloneMultiplier);
		System.out.printf("Giving %s pure damage %d HP to enemy...\n", name, this.inflictDamage());
		enemy.receiveDamage(totalDamage);
	}

	@Override
	public String toString() {
		return String.format("%s (Clan %s) | Level: %d | HP: %d | Skill: %s", name, clan, level, hp,
				attackSkill.toString());
	}

	@Override
	public Ninja clone() throws CloneNotSupportedException {
		return new Ninja(name, clan, level, attackSkill.clone(), hp);
	}
	
	private boolean isDeepCloned(Ninja clone) {
	  return !(this.equals(clone) || attackSkill.equals(clone.attackSkill));
	}
}