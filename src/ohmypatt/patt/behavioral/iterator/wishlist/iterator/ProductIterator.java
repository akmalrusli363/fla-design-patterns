package ohmypatt.patt.behavioral.iterator.wishlist.iterator;

import java.util.List;

import ohmypatt.patt.behavioral.iterator.wishlist.model.Product;

public class ProductIterator implements Iterator<Product> {
	private List<Product> list;
	private int index;

	public ProductIterator(List<Product> list) {
	    this.list = list;
	    index = 0;
	  }

	@Override
	public boolean hasNext() {
		return index < list.size();
	}

	@Override
	public Product next() {
		return hasNext() ? list.get(index++) : null;
	}

	@Override
	public void resetIndex() {
		index = 0;
	}
}