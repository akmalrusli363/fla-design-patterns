package ohmypatt.patt.structural.bridge.color.abstraction;

import ohmypatt.patt.structural.bridge.color.implementator.Color;

public class Square extends Shape {
	public Square(Color color) {
		super(color);
	}

	public void display() {
		System.out.println(getDisplayColor() + " square");
	}
}