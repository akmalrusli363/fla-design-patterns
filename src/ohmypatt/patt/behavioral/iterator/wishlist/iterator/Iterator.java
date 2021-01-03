package ohmypatt.patt.behavioral.iterator.wishlist.iterator;

public interface Iterator<T> {
	/**
	 * Is pointer index in iterator less than list size? Or simple, has any next
	 * data need to iterate in the list?
	 * 
	 * @return pointer index < list.size()?
	 */
	boolean hasNext();

	/**
	 * Get next data iterated from a list. THe index will added after data fetched.
	 * 
	 * @return data fetched from list at pointer index
	 */
	T next();

	/**
	 * Reset pointer index to zero.
	 */
	void resetIndex();
}
