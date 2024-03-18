package util;

public enum PaymentOption {
	STRIPE("Stripe"),
	BITCOIN("Bitcoin"),
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    BANK_TRANSFER("Bank Transfer"),
    PAYPAL("PayPal");

    private final String displayName;

    PaymentOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public static PaymentOption getByDisplayName(String displayName) {
        for (PaymentOption option : PaymentOption.values()) {
            if (option.displayName.equals(displayName)) {
                return option;
            }
        }
        return null;
    }
}
