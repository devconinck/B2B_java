package util;

public enum OrderStatus {
    PLACED("Placed"),
    PROCESSED("Processed"),
    SHIPPED("Shipped"),
    OUT_FOR_DELIVERY("Out for delivery"),
    DELIVERD("Delivered"),
    COMPLETED("Completed");

    private final String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
