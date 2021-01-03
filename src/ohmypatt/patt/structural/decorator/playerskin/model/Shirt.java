package ohmypatt.patt.structural.decorator.playerskin.model;

public class Shirt extends PlayerDecorator {
	private String shirtName;

	public Shirt(Player innerSkin, String shirtName) {
		super(innerSkin);
		this.shirtName = shirtName;
	}

	@Override
	public void deploySkin() {
		innerSkin.deploySkin();
		System.out.println("Wear shirt to player: " + shirtName);
	}
}
