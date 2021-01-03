package ohmypatt.patt.behavioral.state.atm.state;

import ohmypatt.patt.behavioral.state.atm.ATMMachine;

public class NoCard implements ATMState {
	private ATMMachine atm;
	
	public NoCard(ATMMachine atm) {
		this.atm = atm;
	}

	@Override
	public void insertCard() {
		System.out.println("Card inserted!");
		atm.setCurrentState(atm.getHasCardState());
	}

	@Override
	public void ejectCard() {
		System.out.println("There is no card inside this machine!");
	}

	@Override
	public void insertPin(int pin) {
		System.out.println("Please insert your ATM card first!");
	}

	@Override
	public void withdrawCash(int amountOfCash) {
		System.out.println("There is no card inside this machine!");
	}
}
