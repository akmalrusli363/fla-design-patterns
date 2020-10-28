package ohmypatts.patt.creational.singleton.storage;

public class Product {

	private final String name;
	private int quantity;
	
	public Product(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d)", getName(), getQuantity());
	}

	@Override
	public int hashCode() {
		return name.hashCode() + quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj instanceof Product)
			return false;
		Product other = (Product) obj;
		if (name == null && other.name != null)
			return false;
		else if (!(name.equals(other.name) && quantity == other.quantity))
			return false;
		return true;
	}

}
