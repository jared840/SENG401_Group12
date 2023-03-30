package Business.OrderEvents;

import java.util.Date;

import entities.OrderEvent;
import entities.Product;

public class RemoveFromCartEvent extends AddToCartEvent {

	public RemoveFromCartEvent(Product product, int quantity, int orderId, OrderEventTypes eventType, Date timeStamp,
			String userId, String page) {
		super(product, -Math.abs(quantity), orderId, eventType, timeStamp, userId, page);
		// TODO Auto-generated constructor stub
	}

	public RemoveFromCartEvent(OrderEvent e, Product product, int quantity) {
		super(e, product, -Math.abs(quantity));
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getEventInitiatedBy() + " removed " + this.getQuantity() + " of " + this.getProduct().getName()
				+ "from the order.";
	}
}
