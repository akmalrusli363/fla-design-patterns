package ohmypatt.patt.behavioral.chains.atm.chains;

import ohmypatt.patt.behavioral.chains.atm.model.Currency;

public interface DispenseChain {
	void setNextChain(DispenseChain nextChain);

	void dispense(Currency currency);
}