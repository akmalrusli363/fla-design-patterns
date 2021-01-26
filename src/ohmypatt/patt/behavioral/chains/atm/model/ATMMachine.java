package ohmypatt.patt.behavioral.chains.atm.model;

import ohmypatt.patt.behavioral.chains.atm.chains.Dispense100k;
import ohmypatt.patt.behavioral.chains.atm.chains.Dispense10k;
import ohmypatt.patt.behavioral.chains.atm.chains.Dispense20k;
import ohmypatt.patt.behavioral.chains.atm.chains.Dispense50k;
import ohmypatt.patt.behavioral.chains.atm.chains.DispenseChain;
import ohmypatt.patt.behavioral.chains.atm.chains.DispenseNothing;
import ohmypatt.patt.behavioral.chains.atm.chains.MoneyValidator;

public class ATMMachine {
	private DispenseChain getAtmDispenseChains() {
		DispenseChain dispense10k = new Dispense10k(new DispenseNothing());
		DispenseChain dispense20k = new Dispense20k(dispense10k);
		DispenseChain dispense50k = new Dispense50k(dispense20k);
		return new Dispense100k(dispense50k);
	}

	public void dispense(int nominal) {
		Currency currency = new Currency(nominal);
		DispenseChain dispenser = new MoneyValidator(getAtmDispenseChains());
		dispenser.dispense(currency);
	}
}