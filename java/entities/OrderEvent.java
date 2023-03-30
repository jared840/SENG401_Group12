package entities;

import java.util.Date;

import Business.OrderEvents.OrderEventTypes;

public class OrderEvent {

	private int eventId;
	private int orderId;
	private Date timeStamp;
	// Event can be triggered by warehouse etc
	private String eventInitiatedByUser;
	private String eventInitiatedOnPage;
	private String classType;
	// to reconstruct each type of event
	private String jsonClob;

	public OrderEvent(int orderId, OrderEventTypes eventType, Date timeStamp, String userId,
			String eventInitiatedOnPage) {
		this.orderId = orderId;
		this.classType = eventType.toString();
		this.timeStamp = timeStamp;
		this.eventInitiatedByUser = userId;
		this.eventInitiatedOnPage = eventInitiatedOnPage;
	}

	public OrderEvent(int eventId, int orderId, String eventType, Date timeStamp, String userId,
			String eventInitiatedOnPage, String json) {
		this.orderId = orderId;
		this.classType = eventType;
		this.timeStamp = timeStamp;
		this.eventInitiatedByUser = userId;
		this.eventInitiatedOnPage = eventInitiatedOnPage;
		this.eventId = eventId;
		this.jsonClob = json;
	}

	public int getOrderId() {
		return orderId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getEventInitiatedBy() {
		return eventInitiatedByUser;
	}

	public String getClassType() {
		return classType;
	}

	public String getEventInitiatedOnPage() {
		return eventInitiatedOnPage;
	}

	public String getjsonClob() {
		return jsonClob;
	}

	public void setjsonClob(String jsonClob) {
		this.jsonClob = jsonClob;
	}

	public int getEventId() {
		return eventId;
	}

}
