package ohmypatts.patt.creational.abstractfactory.furniture.model;

public interface Furniture {
	public void assemble();
	public String describe();

	@Override
	public String toString();

	default String getFullDescription() {
		return toString();
	}
}