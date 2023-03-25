package Business.OrderEvents;

public enum OrderEventTypes {
	ADD_TO_CART {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return AddToCartEvent.class.getCanonicalName();
		}
	},
	REMVOVE_FROM_CART {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return RemoveFromCartEvent.class.getCanonicalName();
		}
	},
	TRANSACTION_PROCESSED {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ProcessOrderEvent.class.getCanonicalName();
		}
	},
	ORDER_SHIPPED {
		@Override
		public String toString() {
			// TODO Auto-generated method stubOrderShippedEvent
			return OrderShippedEvent.class.getCanonicalName();
		}
	}
}
