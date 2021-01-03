package ohmypatt.patt.behavioral.iterator.wishlist.iterable;
public interface IterableList<T> extends Iterable<T> {
  boolean add(T element);
  boolean remove(T element);
  int size();
}

