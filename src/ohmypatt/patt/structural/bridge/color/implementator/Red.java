package ohmypatt.patt.structural.bridge.color.implementator;

public class Red implements Color {
	@Override
	public String getColor() {
		return "Red";
	}

	@Override
	public String toString() {
		return getColor();
	}
}
