package ohmypatt.patt.structural.decorator.playerskin.model;

public abstract class PlayerDecorator implements Player {
	protected Player innerSkin; // alias wrapee

	public PlayerDecorator(Player innerSkin) {
		this.innerSkin = innerSkin;
	}
}