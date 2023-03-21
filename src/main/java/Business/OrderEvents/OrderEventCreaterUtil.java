package Business.OrderEvents;

import java.util.Date;
import java.util.Map;

import database.DBController;
import entities.Order;
import entities.OrderEvent;
import entities.User;

public class OrderEventCreaterUtil {

	public static OrderEventTypes GetOrderEventType(String eventType) throws Exception {
		if (eventType.equalsIgnoreCase("AddToCart"))
			return OrderEventTypes.ADD_TO_CART;
		else if (eventType.equalsIgnoreCase("RemoveFromCart"))
			return OrderEventTypes.REMVOVE_FROM_CART;
		else
			throw new Exception("No valid event type found");
	}

	public OrderEvent CreateOrderEventFromGUI(User user, DBController db, Map<String, String[]> paramMap)
			throws Exception {
		OrderEventTypes eventType = OrderEventCreaterUtil.GetOrderEventType(paramMap.get("eventType")[0]);

		// TODO: FAKE ORDER ID
		// Order order = db.getOrder(1);
		// TODO: FAKE ORDER ID

		Order order = db.getOrderInCartStage(user.getUser_ID());
		if (order == null)
			order = db.createDefaultOrderInCartStage(user);

		OrderEvent event = new OrderEvent(order.getOrder_ID(), eventType, new Date(), paramMap.get("userId")[0],
				paramMap.get("page")[0]);

		if (event.getClassType().equals(OrderEventTypes.ADD_TO_CART.toString())) {
			return new AddToCartEvent(event, db.getProductById(Integer.parseInt(paramMap.get("productId")[0])),
					Integer.parseInt(paramMap.get("quantity")[0]));

		} else if (event.getClassType().equals(OrderEventTypes.REMVOVE_FROM_CART.toString())) {
			return new RemoveFromCartEvent(event, db.getProductById(Integer.parseInt(paramMap.get("productId")[0])),
					Integer.parseInt(paramMap.get("quantity")[0]));
		} else {
			// should never happen because of the method above
			throw new Exception("No valid event type found");
		}

	}
}
