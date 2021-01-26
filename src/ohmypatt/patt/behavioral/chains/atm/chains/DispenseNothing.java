package ohmypatt.patt.behavioral.chains.atm.chains;

import ohmypatt.patt.behavioral.chains.atm.model.Currency;

public class DispenseNothing implements DispenseChain {
	// May incorrect & causes Refused Bequest smell, but main goal to stop chains
	@Override
	public void setNextChain(DispenseChain nextChain) {
		// nothing
	}

	@Override
	public void dispense(Currency currency) {
		System.out.printf("Nothing to dispense for IDR %d!\n", currency.getAmount());
	}
}
