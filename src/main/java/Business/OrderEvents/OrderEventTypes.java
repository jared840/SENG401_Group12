package Business.OrderEvents;

public enum OrderEventTypes {
	ADD_TO_CART {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return AddToCartEvent.class.getCanonicalName();
		}
	},
	REMVOVE_FROM_CART{
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return RemoveFromCartEvent.class.getCanonicalName();
		}
	}, 
	TRANSACTION_PENDING,
	READY_FOR_WAREHOUSE,
	BEING_PROCESSED_BY_WAREHOUSE, 
	WAITING_FOR_SHIPPING_COMPANY
}
