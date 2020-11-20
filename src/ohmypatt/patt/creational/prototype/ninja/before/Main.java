package ohmypatt.patt.creational.prototype.ninja.before;

public class Main {

	public Main() {
		AttackSkill basicAttack = new AttackSkill("Kagira Attack", 2, 160);
		AttackSkill kugoAttack = new AttackSkill("Kugo Attack", 8, 110);
		Ninja saburo = new Ninja("Uzumaki Saburo", "Uchiha", basicAttack);
		Ninja bayu = new Ninja("Uzumaki Bayu", "Pain-Uchiha", kugoAttack);
		saburo.attack(bayu);
		saburo.performedJutsuAttack(bayu);
	}

	public static void main(String[] args) {
		new Main();
	}

}
