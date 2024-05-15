package util;

public enum NotificationType {
	PAYMENT_REQUEST("Payment Request"), // Only for the customer
	PAYMENT_RECEIVED("Payment Received"), // Only for the supplier
	ORDER_READY("Order Ready"); // Only for the supplier

	private final String value;

	private NotificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
