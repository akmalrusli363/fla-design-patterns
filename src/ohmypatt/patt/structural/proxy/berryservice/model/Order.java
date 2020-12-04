package ohmypatt.patt.structural.proxy.berryservice.model;

public class Order {
	private static int idCount = 0;
	private Product product;
	private final int orderID;
	private int quantity;
	
	public Order(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.orderID = ++idCount;
	}
	
	public int getOrderId() {
		return orderID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
