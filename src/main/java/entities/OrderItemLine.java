package entities;

// NOT A DATABASE MODEL
public class OrderItemLine {

	private Product product;
	private int quantity;
	private double subTotal;

	public OrderItemLine(Product product, int quantity) {

		this.product = product;
		this.quantity = quantity;
		this.subTotal = quantity * product.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		quantity += q;
	}

	public double getsubTotal() {
		return subTotal;
	}

	public void incrementSubtotal(double subTotal) {
		this.subTotal += subTotal;
	}

	public void changeOrderLineValues(int quantity) {
		setQuantity(quantity);
		incrementSubtotal(getSubTotalBasedOnQuantity(quantity));
	}

	private double getSubTotalBasedOnQuantity(int q) {
		return this.product.getPrice() * q;
	}

	@Override
	public String toString() {
		String x = String.format(
				"This order has %d item(s) consisting of the product %s. The sub total for this item is %f",
				this.getQuantity(), this.getProduct().getName(), this.getsubTotal());
		return x;
	}
}
