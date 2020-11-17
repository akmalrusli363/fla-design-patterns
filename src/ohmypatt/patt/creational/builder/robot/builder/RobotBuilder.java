package ohmypatt.patt.creational.builder.robot.builder;

import ohmypatt.patt.creational.builder.robot.model.Robot;

public class RobotBuilder {
	private Robot robot;
	
	public RobotBuilder() {
		this(new Robot());
	}
	
	public RobotBuilder(Robot robot) {
		this.robot = robot;
	}
	
	public RobotBuilder shield(String shield) {
		robot.setShield(shield);
		return this;
	}
	
	public RobotBuilder gun(String gun) {
		robot.setGun(gun);
		return this;
	}
	public RobotBuilder sword(String sword) {
		robot.setSword(sword);
		return this;
	}
	public RobotBuilder brainchip(String brainchip) {
		robot.setBrainchip(brainchip);
		return this;
	}
	
	public Robot getResult() {
		return robot;
	}

}
