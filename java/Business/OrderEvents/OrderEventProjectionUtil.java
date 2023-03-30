package Business.OrderEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.OrderEvent;
import entities.OrderItemLine;

public class OrderEventProjectionUtil {

	public static ArrayList<OrderItemLine> ParseEventsToProducts(ArrayList<OrderEvent> events) {
		ArrayList<OrderItemLine> productList = new ArrayList<>();
		try {
			// get all add product events.
			for (OrderEvent e : events) {
				if (!e.getClassType().equalsIgnoreCase(AddToCartEvent.class.getCanonicalName()))
					continue;

				AddToCartEvent ex = (AddToCartEvent) OrderEventParserUtil.ConvertOrderEventFromClob(e);
				List<OrderItemLine> product = productList.stream()
						.filter(p -> p.getProduct().getProductId() == ex.getProduct().getProductId())
						.collect(Collectors.toList());
				if (product.size() == 0)
					productList.add(new OrderItemLine(ex.getProduct(), ex.getQuantity()));
				else
					product.get(0).changeOrderLineValues(ex.getQuantity());
			}

			for (OrderEvent e : events) {
				if (!e.getClassType().equalsIgnoreCase(RemoveFromCartEvent.class.getCanonicalName()))
					continue;

				RemoveFromCartEvent ex = (RemoveFromCartEvent) OrderEventParserUtil.ConvertOrderEventFromClob(e);
				List<OrderItemLine> product = productList.stream()
						.filter(p -> p.getProduct().getProductId() == ex.getProduct().getProductId())
						.collect(Collectors.toList());
				product.get(0).changeOrderLineValues(ex.getQuantity());
				if (product.get(0).getQuantity() == 0)
					for (int i = 0; i < productList.size(); i++) {
						if (productList.get(i).getProduct().getProductId() == product.get(0).getProduct()
								.getProductId()) {
							productList.remove(i);

						}
					}
			}

		} catch (

		Exception e) {
			// throw new Exception("Could not parse the events, invalid data in the
			// database.");¨
			e.printStackTrace();
		}

		return productList;
	}
}
