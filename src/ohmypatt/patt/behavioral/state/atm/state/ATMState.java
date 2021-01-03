package ohmypatt.patt.behavioral.state.atm.state;

public interface ATMState {
	public void insertCard();
	public void ejectCard();
	public void insertPin(int pin);
	public void withdrawCash(int amountOfCash);
}
