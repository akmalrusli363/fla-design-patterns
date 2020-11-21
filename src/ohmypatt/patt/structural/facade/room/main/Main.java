package ohmypatt.patt.structural.facade.room.main;

import ohmypatt.patt.structural.facade.room.facade.RoomFacader;
import ohmypatt.patt.structural.facade.room.model.*;

public class Main {
	public Main() {
		AC ac = new AC();
		TV tv = new SmartTV();
		Sofa sofa = new Sofa();
		Lamp fan = new Lamp();
		Window window = new Window();

		RoomFacader facade = new RoomFacader(tv, ac, sofa, fan, window);
		facade.sportMode();
		System.out.println();
		facade.gamingMode();		
		System.out.println();
		facade.leaveRoom();		
	}

	public static void main(String[] args) {
		new Main();
	}
}
