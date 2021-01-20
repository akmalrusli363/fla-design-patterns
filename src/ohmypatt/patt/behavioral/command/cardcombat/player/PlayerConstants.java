package ohmypatt.patt.behavioral.command.cardcombat.player;

class PlayerConstants {
	private PlayerConstants() {
	}

	public static final int MAX_HEALTH = 100;

	public static final Damage HIT_DAMAGE = new Damage(3, 5);
	public static final Damage PUNCH_DAMAGE = new Damage(5, 15);
	public static final Damage KICK_DAMAGE = new Damage(5, 10);
}
