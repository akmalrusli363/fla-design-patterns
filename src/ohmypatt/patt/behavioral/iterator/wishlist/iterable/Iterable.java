package ohmypatt.patt.behavioral.iterator.wishlist.iterable;

import ohmypatt.patt.behavioral.iterator.wishlist.iterator.Iterator;

public interface Iterable<T> {
  Iterator<T> createIterator();
}

