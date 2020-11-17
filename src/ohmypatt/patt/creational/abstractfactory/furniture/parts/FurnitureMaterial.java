package ohmypatt.patt.creational.abstractfactory.furniture.parts;

public enum FurnitureMaterial {
	IRISH_WOOD("Irish Pine Wood"),
	SPRUCE_WOOD("Spruce Wood"),
	SPRING_SALTER_COMPOSITE("Spring-salter Wood Composite"),
	TITANIUM_PATINA_COMPOSITE("Titanium-Patina Composite");
	
	private final String type;
	
	private FurnitureMaterial(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return getType();
	}
}
