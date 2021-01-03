package ohmypatt.patt.structural.decorator.playerskin.model;

public class BasePlayer implements Player {
	private String name;
	private int healthPoint, level;

	private static int baseHealthPoint = 100;

	public BasePlayer(String name, int level, int healthPoint) {
		this.name = name;
		this.level = level;
		this.healthPoint = healthPoint;
	}

	public BasePlayer(String name, int level) {
		this(name, level, baseHealthPoint * (level / 5));
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	@Override
	public void deploySkin() {
		System.out.println("Player name : " + name);
		System.out.printf("Player level / HP : Lvl %d (%d HP)\n", level, healthPoint);
		System.out.println("Wear base clothing to player...");
	}
}