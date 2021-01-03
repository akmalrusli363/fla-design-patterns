package ohmypatt.patt.behavioral.state.atm;

import ohmypatt.patt.behavioral.state.atm.state.ATMState;
import ohmypatt.patt.behavioral.state.atm.state.HasCard;
import ohmypatt.patt.behavioral.state.atm.state.HasPin;
import ohmypatt.patt.behavioral.state.atm.state.NoCard;
import ohmypatt.patt.behavioral.state.atm.state.NoCash;

public class ATMMachine {
	private int money;
	private final ATMState noCardState;
	private final ATMState hasCardState;
	private final ATMState hasPinState;
	private final ATMState noCashState;

	private ATMState currentState;

	public ATMMachine(int money) {
		this.money = money;

		noCardState = new NoCard(this);
		hasCardState = new HasCard(this);
		hasPinState = new HasPin(this);
		noCashState = new NoCash(this);

		currentState = noCardState;
	}

	public void insertCard() {
		currentState.insertCard();
	}

	public void ejectCard() {
		currentState.ejectCard();
	}

	public void insertPin(int pin) {
		currentState.insertPin(pin);
	}

	public void withdrawCash(int amountOfCash) {
		currentState.withdrawCash(amountOfCash);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;

		if (money > 100000) {
			setCurrentState(noCardState);
		}
	}

	public ATMState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ATMState currentState) {
		this.currentState = currentState;
	}

	public ATMState getNoCardState() {
		return noCardState;
	}

	public ATMState getHasCardState() {
		return hasCardState;
	}

	public ATMState getHasPinState() {
		return hasPinState;
	}

	public ATMState getNoCashState() {
		return noCashState;
	}
}
