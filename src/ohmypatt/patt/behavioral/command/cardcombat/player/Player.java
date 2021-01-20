package ohmypatt.patt.behavioral.command.cardcombat.player;

public class Player {
	private String name;
	private int hp;
	private boolean defend = false;

	private Player() {
		hp = PlayerConstants.MAX_HEALTH;
	}

	public Player(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public boolean isDefend() {
		return defend;
	}

	private int receiveDamage(Damage d) {
		return d.calculateDamage();
	}

	private void hurt(int damage) {
		if (!defend) {
			hp -= (hp + damage < 0) ? 0 : damage;			
		} defend = false;
	}

	public void hit(Player enemy) {
		int damage = receiveDamage(PlayerConstants.HIT_DAMAGE);
		boolean defend = enemy.isDefend();
		enemy.hurt(damage);
		
		if (defend) {
			System.out.printf("Missed damage to %s by hit!\n", enemy.name);
		} else {
			System.out.printf("Received %d HP damage to %s by hit!\n", damage, enemy.name);			
		}
	}

	public void punch(Player enemy) {
		int damage = receiveDamage(PlayerConstants.PUNCH_DAMAGE);
		boolean defend = enemy.isDefend();
		enemy.hurt(damage);
		
		if (defend) {
			System.out.printf("Missed damage to %s by punch!\n", enemy.name);
		} else {
			System.out.printf("Received %d HP damage to %s by punch!\n", damage, enemy.name);			
		}
	}

	public void kick(Player enemy) {
		int damage = receiveDamage(PlayerConstants.KICK_DAMAGE);
		boolean defend = enemy.isDefend();
		enemy.hurt(damage);
		
		if (defend) {
			System.out.printf("Missed damage to %s by kick!\n", enemy.name);
		} else {
			System.out.printf("Received %d HP damage to %s by kick!\n", damage, enemy.name);			
		}
	}

	public void defend() {
		defend = true;
		System.out.printf("Defending...\n");
	}

}
