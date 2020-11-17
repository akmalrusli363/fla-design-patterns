package ohmypatts.patt.creational.builder.robot.main;

import ohmypatts.patt.creational.builder.robot.director.RobotDirector;
import ohmypatts.patt.creational.builder.robot.model.Robot;

public class Main {

	public Main() {
		Robot bot = RobotDirector.getInstance().CasualRobot();
		System.out.println(bot.toString());
	}

	public static void main(String[] args) {
		new Main();
	}

}
