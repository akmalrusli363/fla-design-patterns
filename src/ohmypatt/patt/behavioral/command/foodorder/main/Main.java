package ohmypatt.patt.behavioral.command.foodorder.main;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.command.foodorder.command.AddOrder;
import ohmypatt.patt.behavioral.command.foodorder.command.ExecuteOrder;
import ohmypatt.patt.behavioral.command.foodorder.command.OrderCommand;
import ohmypatt.patt.behavioral.command.foodorder.model.Menu;
import ohmypatt.patt.behavioral.command.foodorder.model.Order;
import ohmypatt.patt.behavioral.command.foodorder.model.Waiter;

public class Main {
	public Main() {
		basicCase();
		batchedCase();
	}

	private void batchedCase() {
		Menu nasiGoreng = new Menu("Nasi Goreng", 13000);
		Menu tempeGoreng = new Menu("Tempe Goreng", 6000);
		Menu tehTawar = new Menu("Teh Tawar", 3000);

		ArrayList<OrderCommand> commands = new ArrayList<>();
		commands.add(new AddOrder(new Order(nasiGoreng, 2)));
		commands.add(new AddOrder(new Order(tempeGoreng, 1)));
		commands.add(new AddOrder(new Order(tehTawar, 2)));
		commands.add(new ExecuteOrder());

		Waiter waiter = new Waiter();
		waiter.executeBatch(commands);
	}
	
	private void basicCase() {
		Menu nasiPadangTelor = new Menu("Nasi Padang Telor", 14000);
		Menu nasiPadangRendang = new Menu("Nasi Padang Rendang", 17000);
		Menu nasiGoreng = new Menu("Nasi Goreng", 13000);

		Waiter waiter = new Waiter();

		waiter.addOrder(new Order(nasiPadangTelor, 2));
		waiter.displayOrderedMenu();
		
		waiter.addOrder(new Order(nasiPadangRendang, 1));
		waiter.displayOrderedMenu();
		
		waiter.executeOrder();
		waiter.displayOrderedMenu();

		waiter.addOrder(new Order(nasiGoreng, 1));
		waiter.displayOrderedMenu();
		
		waiter.executeOrder();
		waiter.displayOrderedMenu();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
