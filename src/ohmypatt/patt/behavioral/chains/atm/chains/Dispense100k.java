package ohmypatt.patt.behavioral.chains.atm.chains;

import ohmypatt.patt.behavioral.chains.atm.model.Currency;

public class Dispense100k implements DispenseChain {
	private DispenseChain chain;

	public Dispense100k(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void dispense(Currency currency) {
		int amount = currency.getAmount();
		if (amount >= 100000) {
			int numOfBanknotes = amount / 100000;
			int remainder = amount % 100000;
			System.out.printf("Dispensed IDR 100k @ %d notes!\n", numOfBanknotes);
			if (remainder != 0) {
				this.chain.dispense(new Currency(remainder));
			}
		} else {
			this.chain.dispense(currency);
		}
	}
}
