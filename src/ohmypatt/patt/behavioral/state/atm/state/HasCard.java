package ohmypatt.patt.behavioral.state.atm.state;

import ohmypatt.patt.behavioral.state.atm.ATMMachine;

public class HasCard implements ATMState {
	private ATMMachine atm;

	public HasCard(ATMMachine atm) {
		this.atm = atm;
	}

	@Override
	public void insertCard() {
		System.out.println("Card is already inserted!");
	}

	@Override
	public void ejectCard() {
		System.out.println("Card ejected!");
		atm.setCurrentState(atm.getNoCardState());
	}

	@Override
	public void insertPin(int pin) {
		if (Integer.toString(pin).length() == 6) {
			System.out.println("PIN Valid!");
			atm.setCurrentState(atm.getHasPinState());
		} else {
			System.out.println("Invalid PIN!");			
		}
	}

	@Override
	public void withdrawCash(int amountOfCash) {
		System.out.println("Please input your PIN first!");
	}
}
