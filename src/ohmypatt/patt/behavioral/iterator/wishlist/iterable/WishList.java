package ohmypatt.patt.behavioral.iterator.wishlist.iterable;

import java.util.Vector;

import ohmypatt.patt.behavioral.iterator.wishlist.iterator.Iterator;
import ohmypatt.patt.behavioral.iterator.wishlist.iterator.ProductIterator;
import ohmypatt.patt.behavioral.iterator.wishlist.model.Product;

public class WishList implements IterableList<Product> {
	private Vector<Product> products;

	public WishList() {
		products = new Vector<Product>();
	}

	@Override
	public Iterator<Product> createIterator() {
		return new ProductIterator(products);
	}

	@Override
	public boolean add(Product element) {
		return products.add(element);
	}

	@Override
	public boolean remove(Product element) {
		return products.remove(element);
	}

	@Override
	public int size() {
		return products.size();
	}
}