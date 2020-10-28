package ohmypatts.patt.creational.abstractfactory.furniture.parts;

public enum FurnitureCapacity {
	S(1, "Small (personal-seater)"), M(2, "Medium (couple-seater)"), L(3, "Large (party-seater)"), XL(4, "Xtra-Large (family seater)");

	private final String scaleName;
	private final int capacity;

	private FurnitureCapacity(int scale, String scaleName) {
		this.scaleName = scaleName;
		this.capacity = scale;
	}

	public String getScaleName() {
		return scaleName;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return getScaleName() + " - " + getCapacity() +  " seats";
	}
}
