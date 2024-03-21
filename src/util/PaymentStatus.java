package util;

public enum PaymentStatus {
    UNPROCESSED("Unprocessed"),
    INVOICE_SENT("Invoice sent"),
    PAID("Paid");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}