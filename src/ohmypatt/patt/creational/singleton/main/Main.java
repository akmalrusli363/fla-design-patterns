package ohmypatt.patt.creational.singleton.main;

import java.util.ArrayList;

import ohmypatt.patt.creational.singleton.storage.Product;
import ohmypatt.patt.creational.singleton.storage.Storage;

public class Main {

	public Main() {
		Storage storage = Storage.getInstance();
		Storage storage2 = Storage.getInstance();
		
		Product torch = new Product("Torch", 22);
		storage.addProduct(new Product("Cobblestone", 32));
		storage.addProduct(new Product("Brick", 16));
		storage.addProduct(new Product("Diamond", 12));
		storage.addProduct(new Product("Coal", 64));
		storage.addProduct(torch);
		
		System.out.println("Storage 1:");
		System.out.println(displayDatas(storage.getProducts()));
		
		storage2.addProduct(new Product("Coffee beans", 6));
		storage2.addProduct(new Product("Iced tea", 10));
		storage2.addProduct(new Product("Berry milk tea", 2));
		storage2.addProduct(new Product("Hojicha tea", 9));
		
		System.out.println("Storage 2:");
		System.out.println(displayDatas(storage.getProducts()));
		
		compareStorage(storage, storage2);
		System.out.println("----------------------------------------------");
		System.out.println();
		
		storage.addProduct(new Product("Iron ore", 20));
		storage.addProduct(new Product("Redstone dust", 41));

		System.out.println("Storage 1 after add 20 iron ore & 41 redstone dust:");
		System.out.println(displayDatas(storage.getProducts()));
		
		compareStorage(storage, storage2);
		System.out.println("----------------------------------------------");
		System.out.println();
		
		storage.addProduct(new Product("USB charger", 3));

		System.out.println("Storage 2 after add 3 USB charger:");
		System.out.println(displayDatas(storage2.getProducts()));
		
		compareStorage(storage, storage2);
		System.out.println("----------------------------------------------");
		System.out.println();
		
		storage.removeProduct(torch);

		System.out.println("Storage 1 after torch removal:");
		System.out.println(displayDatas(storage.getProducts()));

		System.out.println("Storage 2:");
		System.out.println(displayDatas(storage.getProducts()));
		
		compareStorage(storage, storage2);
	}

	private void compareStorage(Storage storage, Storage storage2) {
		System.out.println("Storage 1 == 2? " + storage.equals(storage2));
	}
	
	private String displayDatas(ArrayList<Product> productList) {
		StringBuffer sb = new StringBuffer();
		for (Product product : productList) {
			sb.append(product.toString()).append("\n");			
		} return sb.toString();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
