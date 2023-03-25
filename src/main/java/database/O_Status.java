package database;

public enum O_Status {
	IN_CART {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "cart";
		}
	},
	TRANSACTION_PROCESSED {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "processed";
		}
	},
	WAREHOUSE {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "warehouse";
		}
	},
	SHIPPED {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "shipped";
		}
	}
}
