package Business.OrderEvents;

import java.util.Date;

import entities.OrderEvent;

public class ProcessOrderEvent extends OrderEvent {
	private int CreditCardUsed;

	public ProcessOrderEvent(int creditCardNo, int eventId, int orderId, String eventType, Date timeStamp,
			String userId, String eventInitiatedOnPage, String json) {
		super(eventId, orderId, eventType, timeStamp, userId, eventInitiatedOnPage, json);
		this.CreditCardUsed = creditCardNo;
		this.setjsonClob(OrderEventParserUtil.ConvertOrderEventToJson(this));
	}

	public ProcessOrderEvent(int creditCardNo, OrderEvent e) {
		super(e.getEventId(), e.getOrderId(), e.getClassType(), e.getTimeStamp(), e.getEventInitiatedBy(),
				e.getEventInitiatedOnPage(), e.getjsonClob());
		this.CreditCardUsed = creditCardNo;
		this.setjsonClob(OrderEventParserUtil.ConvertOrderEventToJson(this));
	}

	public int getCreditCardUsed() {
		return CreditCardUsed;
	}

	@Override
	public String toString() {
		String x = String.format(
				"[%s] The user with id %s changed this order by completing the transaction. The order is now ready for the warehouse.",
				this.getTimeStamp().toString(), this.getEventInitiatedBy());
		return x;
	}
}
