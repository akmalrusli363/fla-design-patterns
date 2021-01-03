package ohmypatt.patt.behavioral.iterator.wishlist.model;

public class Product {
	private String name, description;
	private int price;

	public Product(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}
}