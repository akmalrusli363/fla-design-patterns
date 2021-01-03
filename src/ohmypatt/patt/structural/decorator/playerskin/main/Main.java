package ohmypatt.patt.structural.decorator.playerskin.main;

import ohmypatt.patt.structural.decorator.playerskin.model.BasePlayer;
import ohmypatt.patt.structural.decorator.playerskin.model.Hat;
import ohmypatt.patt.structural.decorator.playerskin.model.Player;
import ohmypatt.patt.structural.decorator.playerskin.model.Shirt;

public class Main {
	public Main() {
		Player ucok = new BasePlayer("Ucok", 2000, 99);
		ucok = new Shirt(ucok, "Supreme T-Shirt");
		ucok = new Hat(ucok, "Topi tentara", "military-type");
		ucok.deploySkin();
	}

	public static void main(String[] args) {
		new Main();
	}
}
