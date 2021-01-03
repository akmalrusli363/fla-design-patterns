package ohmypatt.patt.structural.decorator.playerskin.model;

public class Hat extends PlayerDecorator {
	private String hatName, hatType;

	public Hat(Player innerSkin, String hatName, String hatType) {
		super(innerSkin);
		this.hatName = hatName;
		this.hatType = hatType;
	}

	@Override
	public void deploySkin() {
		innerSkin.deploySkin();
		System.out.println("Wear " + hatType + " hat to player: " + hatName);
	}
}