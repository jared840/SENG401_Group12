package Business.OrderEvents;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.OrderEvent;

public class OrderEventParserUtil {

	// 'Mar 19, 2023, 12:52:16 AM'
	private static final Gson JSON_PARSER = new GsonBuilder().setDateFormat("MMM dd, yyyy, h:mm:ss").create();

	public static String EncodeEventToJsonAndBase64(String json) {
		return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(json.getBytes());
	}

	public static String DecodeEventToJsonAndBase64(String josnClob) {
		byte[] bytes = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(josnClob);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public static String ConvertOrderEventToJson(OrderEvent e) {
		return JSON_PARSER.toJson(e);
	}

	public static ArrayList<OrderEvent> ConvertOrderEventListFromClob(ArrayList<OrderEvent> list) {
		ArrayList<OrderEvent> parsedEvents = new ArrayList<OrderEvent>();
		if (list.size() == 0)
			return parsedEvents;

		for (OrderEvent e : list) {
			if (e.getClassType().equalsIgnoreCase(AddToCartEvent.class.getCanonicalName())) {
				AddToCartEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), AddToCartEvent.class);
				parsedEvents.add(new AddToCartEvent(e, parsed.getProduct(), parsed.getQuantity()));
			} else if (e.getClassType().equalsIgnoreCase(RemoveFromCartEvent.class.getCanonicalName())) {
				RemoveFromCartEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), RemoveFromCartEvent.class);
				parsedEvents.add(new AddToCartEvent(e, parsed.getProduct(), parsed.getQuantity()));
			} else if (e.getClassType().equalsIgnoreCase(ProcessOrderEvent.class.getCanonicalName())) {
				ProcessOrderEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), ProcessOrderEvent.class);
				parsedEvents.add(new ProcessOrderEvent(parsed.getCreditCardUsed(), e));
			} else if (e.getClassType().equalsIgnoreCase(OrderShippedEvent.class.getCanonicalName())) {
				parsedEvents.add(new OrderShippedEvent(e));
			}
		}
		return parsedEvents;
	}

	public static OrderEvent ConvertOrderEventFromClob(OrderEvent e) throws Exception {

		if (e.getClassType().equalsIgnoreCase(AddToCartEvent.class.getCanonicalName())) {
			AddToCartEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), AddToCartEvent.class);
			return new AddToCartEvent(e, parsed.getProduct(), parsed.getQuantity());
		} else if (e.getClassType().equalsIgnoreCase(RemoveFromCartEvent.class.getCanonicalName())) {
			RemoveFromCartEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), RemoveFromCartEvent.class);
			return new RemoveFromCartEvent(e, parsed.getProduct(), parsed.getQuantity());
		} else if (e.getClassType().equalsIgnoreCase(ProcessOrderEvent.class.getCanonicalName())) {
			ProcessOrderEvent parsed = JSON_PARSER.fromJson(e.getjsonClob(), ProcessOrderEvent.class);
			return new ProcessOrderEvent(parsed.getCreditCardUsed(), e);
		} else if (e.getClassType().equalsIgnoreCase(OrderShippedEvent.class.getCanonicalName())) {
			return new OrderShippedEvent(e);
		} else {

			throw new Exception(
					"An error has occurred while trying to reconstruct an event, on event id: " + e.getEventId());
		}

	}

}
