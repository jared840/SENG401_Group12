package Business.OrderEvents;

import java.util.Date;

import entities.OrderEvent;
import entities.Product;

public class AddToCartEvent extends OrderEvent {
	private int quantity;
	private Product product;

	public AddToCartEvent(Product product, int quantity, int orderId, OrderEventTypes eventType, Date timeStamp,
			String userId, String page) {
		super(orderId, eventType, timeStamp, userId, page);
		this.product = product;

		this.quantity = quantity;
		this.setjsonClob(OrderEventParserUtil.ConvertOrderEventToJson(this));
	}

	public AddToCartEvent(OrderEvent e, Product product, int quantity) {
		super(e.getEventId(), e.getOrderId(), e.getClassType(), e.getTimeStamp(), e.getEventInitiatedBy(),
				e.getEventInitiatedOnPage(), e.getjsonClob());
		this.product = product;
		this.quantity = quantity;
		this.setjsonClob(OrderEventParserUtil.ConvertOrderEventToJson(this));
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String addedOrRemovded = this.getQuantity() < 0 ? " removing " : "adding";
		String x = String.format(
				"[%s] The user with id %s changed the order by %s %d item(s). This event was triggered on the page; %s",
				this.getTimeStamp().toString(), this.getEventInitiatedBy(), addedOrRemovded, Math.abs(quantity),
				this.getEventInitiatedOnPage());
		return x;
	}
}
