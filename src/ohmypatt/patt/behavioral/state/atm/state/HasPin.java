package ohmypatt.patt.behavioral.state.atm.state;

import ohmypatt.patt.behavioral.state.atm.ATMMachine;

public class HasPin implements ATMState {
	private ATMMachine atm;

	public HasPin(ATMMachine atm) {
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
		System.out.println("You've already inputted PIN!");
	}

	@Override
	public void withdrawCash(int amountOfCash) {
		if (amountOfCash < 10000) {
			System.out.println("Please input your withdraw amount at least Rp10000!");
		} else if (amountOfCash > atm.getMoney()) {
			System.out.println("Insufficient ATM amount to withdraw your cash!");
		} else {
			int remaining = atm.getMoney() - amountOfCash;
			
			atm.setMoney(remaining);
			
			System.out.println("Withdrawn Rp" + amountOfCash + " from card!");
			System.out.println("Remaining: Rp" + remaining);
			
			ejectCard();;
			
			if (remaining <= 100000) {
				atm.setCurrentState(atm.getNoCashState());
				System.out.println("ATM is in maintenance!");
			}
		}
	}
}
