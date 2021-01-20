package ohmypatt.patt.behavioral.command.cardcombat.main;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.command.cardcombat.command.*;
import ohmypatt.patt.behavioral.command.cardcombat.player.Player;

public class Main {
	private Player player1, player2;
	
	public Main() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		
		Command cp1 = new DefendCommand(player1);
		Command cp2 = new HitCommand(player2, player1);
		executePairs(cp1, cp2);
		
		cp1 = new HitCommand(player1, player2);
		cp2 = new HitCommand(player2, player1);
		executePairs(cp1, cp2);

		cp1 = new PunchCommand(player1, player2);
		cp2 = new KickCommand(player2, player1);
		executePairs(cp1, cp2);
		
		cp1 = new PunchCommand(player1, player2);
		cp2 = new DefendCommand(player2);
		executePairs(cp1, cp2);
		
		Command[] combo = {
				new KickCommand(player1, player2),
				new KickCommand(player1, player2),
				new PunchCommand(player1, player2),
		};
		cp1 = new ComboAttackCommand(createCombinations(combo));
		cp2 = new PunchCommand(player2, player1);
		executePairs(cp1, cp2);
	}
	
	private void executePairs(Command player1Command, Command player2Command) {
		player1Command.execute();
		displayPlayerStatus();
		player2Command.execute();
		displayPlayerStatus();
	}
	
	private void displayPlayerStatus() {
		System.out.println(player1.getName() + " HP: " + player2.getHp());
		System.out.println(player2.getName() + " HP: " + player2.getHp());
		System.out.println();
	}
	
	private ArrayList<Command> createCombinations(Command[] commands) {
		ArrayList<Command> combo = new ArrayList<>();
		for (Command command : commands) {
			combo.add(command);
		} return combo;
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
