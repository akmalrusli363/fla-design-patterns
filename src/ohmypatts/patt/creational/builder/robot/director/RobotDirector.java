package ohmypatts.patt.creational.builder.robot.director;

import ohmypatts.patt.creational.builder.robot.builder.RobotBuilder;
import ohmypatts.patt.creational.builder.robot.model.Robot;

public class RobotDirector {

	// Declare Singleton
	private static RobotDirector director = null;
	
	private RobotDirector() {}
	
	public static synchronized RobotDirector getInstance() {
		if (director == null) {
			director = new RobotDirector();
		}
		return director;
	}
	
	public Robot CasualRobot() {
		RobotBuilder builder = new RobotBuilder();
		builder.brainchip("Kasula Brain");
		builder.sword("Taito Ward");
		builder.gun("P30");
		
		return builder.getResult();
	}
	
	public Robot RereRobot() {
		RobotBuilder robotBuilder = new RobotBuilder();
		Robot bot = robotBuilder.brainchip("i3").gun("Desert Eagle").getResult();
		return bot;
	}
	
	//
}
