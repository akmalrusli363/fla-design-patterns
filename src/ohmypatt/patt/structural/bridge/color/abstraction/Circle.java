package ohmypatt.patt.structural.bridge.color.abstraction;

import ohmypatt.patt.structural.bridge.color.implementator.Color;

public class Circle extends Shape {
	public Circle(Color color) {
		super(color);
	}

	public void display() {
		System.out.println(getDisplayColor() + " circle");
	}
}
