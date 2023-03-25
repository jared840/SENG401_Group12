package Business.OrderEvents;

import java.util.Date;

import entities.OrderEvent;

public class OrderShippedEvent extends OrderEvent {

	public OrderShippedEvent(int orderId, OrderEventTypes eventType, Date timeStamp, String userId,
			String eventInitiatedOnPage) {
		super(orderId, eventType, timeStamp, userId, eventInitiatedOnPage);
		// TODO Auto-generated constructor stub
	}

	public OrderShippedEvent(int eventId, int orderId, String eventType, Date timeStamp, String userId,
			String eventInitiatedOnPage, String json) {
		super(eventId, orderId, eventType, timeStamp, userId, eventInitiatedOnPage, json);
		// TODO Auto-generated constructor stub
	}

	public OrderShippedEvent(OrderEvent e) {
		super(e.getEventId(), e.getOrderId(), e.getClassType(), e.getTimeStamp(), e.getEventInitiatedBy(),
				e.getEventInitiatedOnPage(), e.getjsonClob());
		this.setjsonClob(OrderEventParserUtil.ConvertOrderEventToJson(this));
	}

}
