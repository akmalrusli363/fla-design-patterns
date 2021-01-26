package ohmypatt.patt.behavioral.chains.atm.chains;

import ohmypatt.patt.behavioral.chains.atm.model.Currency;

public class MoneyValidator implements DispenseChain {
	private DispenseChain chain;

	public MoneyValidator(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void dispense(Currency currency) {
		if (currency.getAmount() % 10000 != 0) {
			System.out.println("Amount must be 10k multipler!");
		} else {
			this.chain.dispense(currency);
		}
	}
}