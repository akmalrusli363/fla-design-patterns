package ohmypatt.patt.behavioral.iterator.wishlist.iterator;

import java.util.List;
import java.util.Vector;

import ohmypatt.patt.behavioral.iterator.wishlist.model.Product;

public class WishlistIterator implements Iterator<Product> {
	private Vector<Vector<Product>> nestedList;
	private int allIndex, outerIndex, innerIndex;
	private List<Product> innerList;

	public WishlistIterator(Vector<Vector<Product>> nestedList) {
		this.nestedList = nestedList;
		allIndex = 0;
		innerIndex = 0;
		outerIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return allIndex < getWholeListSize();
	}

	private boolean hasNext(List<Product> innerList) {
		return innerIndex < innerList.size();
	}

	private boolean hasNextList() {
		return outerIndex < nestedList.size();
	}

	@Override
	public Product next() {
		if (hasNext(innerList)) {
			return innerList.get(innerIndex);
		} else if (hasNextList()) {
			innerList = nestedList.get(outerIndex++);
			return next();
		}
		return null;
	}

	@Override
	public void resetIndex() {
		allIndex = innerIndex = outerIndex = 0;
	}

	private int getWholeListSize() {
		int totalSize = 0;
		for (List<Product> innerList : nestedList) {
			totalSize += innerList.size();
		}
		return totalSize;
	}
}