package ohmypatt.patt.structural.facade.room.facade;

import ohmypatt.patt.structural.facade.room.model.*;

public class RoomFacader {
	private TV tv;
	private AC ac;
	private Sofa sofa;
	private Lamp lamp;
	private Window window;

	public RoomFacader(TV tv, AC ac, Sofa sofa, Lamp lamp, Window window) {
		this.tv = tv;
		this.ac = ac;
		this.sofa = sofa;
		this.lamp = lamp;
		this.window = window;
	}

	public void sportMode() {
		tv.powerOn();
		tv.tvMode();
		tv.setChannel("Bola TV");
		lamp.turnOn();
		ac.turnOn();
		ac.warmer();
		sofa.clean();
		window.close();
	}
	
	public void gamingMode() {
		tv.powerOn();
		tv.consoleMode();
		lamp.turnOn();
		ac.turnOn();
		ac.cooler();
		sofa.clean();
		window.close();
	}
	
	public void leaveRoom() {
		tv.powerOff();
		lamp.turnOff();
		ac.turnOff();
		sofa.clean();
		window.open();
	}
}
