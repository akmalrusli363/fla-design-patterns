package ohmypatt.patt.behavioral.state.atm.state;

import ohmypatt.patt.behavioral.state.atm.ATMMachine;

public class NoCash implements ATMState {
	private ATMMachine atm;

	public NoCash(ATMMachine atm) {
		this.atm = atm;
	}

	@Override
	public void insertCard() {
		System.out.println("Insufficient ATM Money, unable to perform transaction!");
	}

	@Override
	public void ejectCard() {
		System.out.println("Card ejected!");
		atm.setCurrentState(atm.getNoCardState());
	}

	@Override
	public void insertPin(int pin) {
		System.out.println("You've already inputted PIN!");
	}

	@Override
	public void withdrawCash(int amountOfCash) {
		System.out.println("Insufficient ATM amount to withdraw your cash!");
	}
}
