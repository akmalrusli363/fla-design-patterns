package ohmypatt.patt.behavioral.chains.atm.main;

import java.util.Scanner;

import ohmypatt.patt.behavioral.chains.atm.model.ATMMachine;

public class Main {
	private Scanner scan = new Scanner(System.in);

	public Main() {
		ATMMachine machine = new ATMMachine();
		while (true) {
			System.out.print("Enter amount to dispense: ");
			int amount = scan.nextInt();
			machine.dispense(amount);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
