package ohmypatts.patt.creational.singleton.storage;

import java.util.ArrayList;

/**
 * Singleton is a design pattern which restricts the initialization of
 * a class to ensure that only one instance of the class can be created.
 * 
 * @author akmalrusli363
 *
 */
public class Storage {
	/**
     * An instance for Storage, initially to be null before client class invoke to define this object for first time!
	 * For next declarations, it will return this object defined from this class since first declaration.
	 * 
	 * For better threading, use 'volatile' for better accessibility between threads from main memory.
     */
	private static volatile Storage storageInstance;
	private ArrayList<Product> product;
	
	/**
	 * Must be private to prevent any re-declaration for that object
	 */
	private Storage() {
		product = new ArrayList<>();
	}

	/**
	 * Call for instance of Singleton class. If null, declare the instance before return for instance.
	 * 
	 * For better threading, use 'synchronized' keyword for better synchronization while interacting with other threads.
	 *
	 * @return instance for Storage object
	 */
	public synchronized static Storage getInstance() {
		if (storageInstance == null) {
			storageInstance = new Storage();
		}
		return storageInstance;
	}

	public ArrayList<Product> getProducts() {
		return product;
	}

	public void addProduct(Product product) {
		this.product.add(product);
	}

	public void removeProduct(Product product) {
		this.product.remove(product);
	}
	
}