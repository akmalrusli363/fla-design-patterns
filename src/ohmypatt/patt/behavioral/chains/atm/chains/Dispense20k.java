package ohmypatt.patt.behavioral.chains.atm.chains;

import ohmypatt.patt.behavioral.chains.atm.model.Currency;

public class Dispense20k implements DispenseChain {
	private DispenseChain chain;

	public Dispense20k(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void dispense(Currency currency) {
		int amount = currency.getAmount();
		if (amount >= 20000) {
			int numOfBanknotes = amount / 20000;
			int remainder = amount % 20000;
			System.out.printf("Dispensed IDR 20k @ %d notes!\n", numOfBanknotes);
			if (remainder != 0) {
				this.chain.dispense(new Currency(remainder));
			}
		} else {
			this.chain.dispense(currency);
		}
	}
}
