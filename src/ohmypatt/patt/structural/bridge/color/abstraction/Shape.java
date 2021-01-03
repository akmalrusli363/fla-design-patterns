package ohmypatt.patt.structural.bridge.color.abstraction;

import ohmypatt.patt.structural.bridge.color.implementator.Color;

/**
 * Sebuah Abstraksi yang akan digunakan oleh User/Client
 */
public abstract class Shape {
	/**
	 * Contains attributes from implementation hierarchy
	 */
	private Color color;

	/**
	 * Define shape, with color object as parameter using dependency injection
	 * 
	 * @param color color object to define it's shape color
	 */
	public Shape(Color color) {
		this.color = color;
	}

	public abstract void display();

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public String getDisplayColor() {
		return color.toString();
	}
}
