package ohmypatt.patt.behavioral.iterator.wishlist.iterable;

import java.util.Vector;

import ohmypatt.patt.behavioral.iterator.wishlist.iterator.Iterator;
import ohmypatt.patt.behavioral.iterator.wishlist.iterator.WishlistIterator;
import ohmypatt.patt.behavioral.iterator.wishlist.model.Product;

public class CategorizedWishList implements IterableList<Product> {
	private Vector<Vector<Product>> productSets;

	public CategorizedWishList() {
		this.productSets = new Vector<Vector<Product>>();
	}

	public CategorizedWishList(Vector<Product> products) {
		this();
		this.productSets.add(products);
	}

	@Override
	public Iterator<Product> createIterator() {
		return new WishlistIterator(productSets);
	}

	/**
	 * Get all products by iterating through categories and return as list of
	 * products wishlisted by user without needs of category.
	 */
	public Vector<Product> getAll() {
		Vector<Product> vec = new Vector<>();
		// Using default Iterator applied to Vector<> (inherited from List<>)
		for (Vector<Product> category : productSets) {
			vec.addAll(category);
		}
		return vec;
	}

	/**
	 * In the example documentation, they added the default and uncategorized item
	 * Vector to store inserted products into first index of nested Vector.
	 */
	public Vector<Product> getUncategorizedProducts() {
		if (productSets.isEmpty()) {
			productSets.add(new Vector<Product>());
		}
		return productSets.get(0);
	}

	/**
	 * Add product to first index of nested Vector (uncategorized)
	 */
	@Override
	public boolean add(Product element) {
		return getUncategorizedProducts().add(element);
	}

	/**
	 * Remove product from nested Vector by iterating through all lists.
	 */
	@Override
	public boolean remove(Product element) {
		for (Vector<Product> category : productSets) {
			if (category.remove(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Display number of products NOT number of category in nested vector
	 */
	@Override
	public int size() {
		int totalItems = 0;
		for (Vector<Product> category : productSets) {
			totalItems += category.size();
		}
		return totalItems;
	}
}