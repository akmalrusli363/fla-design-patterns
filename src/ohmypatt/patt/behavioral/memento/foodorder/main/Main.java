package ohmypatt.patt.behavioral.memento.foodorder.main;

import ohmypatt.patt.behavioral.memento.foodorder.model.Menu;
import ohmypatt.patt.behavioral.memento.foodorder.model.Order;
import ohmypatt.patt.behavioral.memento.foodorder.model.Waiter;

public class Main {
	public Main() {
		basicCase();
	}

	private void basicCase() {
		Menu nasiPadang = new Menu("Nasi Padang", 17000);
		Menu indomie = new Menu("Indomie goreng", 7000);
		Menu boba = new Menu("Boba", 27000);
		
		Waiter waiter = new Waiter();
		waiter.addOrder(new Order(nasiPadang, 2));
		waiter.displayOrderedMenu();

		waiter.addOrder(new Order(indomie, 4));
		waiter.displayOrderedMenu();
		
		waiter.undo();
		waiter.displayOrderedMenu();
		
		waiter.addOrder(new Order(boba, 1));
		waiter.displayOrderedMenu();
		
		waiter.executeOrder();
		waiter.displayOrderedMenu();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
