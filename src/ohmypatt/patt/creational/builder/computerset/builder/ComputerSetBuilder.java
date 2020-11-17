package ohmypatt.patt.creational.builder.computerset.builder;

import ohmypatt.patt.creational.builder.computerset.model.Computer;
import ohmypatt.patt.creational.builder.computerset.model.ComputerSet;

public class ComputerSetBuilder {

	private ComputerSet computerSet;
	
	public ComputerSetBuilder() {
		this(new ComputerSet());
	}

	public ComputerSetBuilder(ComputerSet computerSet) {
		this.computerSet = computerSet;
	}

	public void computer(Computer computer) {
		computerSet.setComputer(computer);
	}

	public void keyboard(String keyboard) {
		computerSet.setKeyboard(keyboard);
	}
	
	public void mouse(String mouse) {
		computerSet.setMouse(mouse);
	}
	
	public void speaker(String speaker) {
		computerSet.setSpeaker(speaker);
	}
	
	public void monitor(String monitor) {
		computerSet.setMonitor(monitor);
	}
	
	
	public ComputerSet getResult() {
		return computerSet;
	}
	
	public void resetBuilder() {
		computerSet = new ComputerSet();
	}
	
}
